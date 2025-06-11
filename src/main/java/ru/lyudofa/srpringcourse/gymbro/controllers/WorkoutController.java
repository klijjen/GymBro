package ru.lyudofa.srpringcourse.gymbro.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.lyudofa.srpringcourse.gymbro.model.User;
import ru.lyudofa.srpringcourse.gymbro.model.Workout;
import ru.lyudofa.srpringcourse.gymbro.services.AuthService;
import ru.lyudofa.srpringcourse.gymbro.services.UserService;
import ru.lyudofa.srpringcourse.gymbro.services.WorkoutService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;
    private final UserService userService;
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<?> listUserWorkouts(@RequestHeader("Authorization") String token) {
        User user = userService.findByUsername(authService.getUsernameByToken(token))
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));

        List<Workout> workouts = workoutService.getWorkoutsByUser(user);
        return ResponseEntity.ok(workouts);
    }

    @PostMapping
    public ResponseEntity<?> createWorkout(@RequestHeader("Authorization") String token, @RequestBody Workout request) {
        User user = userService.findByUsername(authService.getUsernameByToken(token))
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));

        workoutService.createWorkout(user, request.getName(), request.getStartTime(),
                request.getEndTime(), request.getNotes());

        return ResponseEntity.ok(Map.of("message", "Тренировка успешно создана"));
    }
}
