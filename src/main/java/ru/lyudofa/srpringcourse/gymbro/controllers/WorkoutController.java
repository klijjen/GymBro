package ru.lyudofa.srpringcourse.gymbro.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.lyudofa.srpringcourse.gymbro.model.User;
import ru.lyudofa.srpringcourse.gymbro.model.Workout;
import ru.lyudofa.srpringcourse.gymbro.services.UserService;
import ru.lyudofa.srpringcourse.gymbro.services.WorkoutService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final UserService userService;

    @GetMapping
    public String listUserWorkouts(@AuthenticationPrincipal UserDetails userDetails,
                                   Model model) {
        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Пользователь ненайлен"));

        List<Workout> workouts = workoutService.getWorkoutsByUser(user);
        model.addAttribute("workouts", workouts);
        return "workouts/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("workout", new Workout());
        return "workouts/create";
    }

    @PostMapping
    public String createWorkout(@AuthenticationPrincipal UserDetails userDetails,
                                @RequestParam String name,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,
                                @RequestParam(required = false) String notes,
                                RedirectAttributes redirectAttributes) {
        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Пользователь ненайден"));
        workoutService.createWorkout(user, name, startTime, endTime, notes);
        redirectAttributes.addFlashAttribute("successMessage", "Тренировка создана");
        return "redirect:/workouts";


    }
}
