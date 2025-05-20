package ru.lyudofa.srpringcourse.gymbro.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import ru.lyudofa.srpringcourse.gymbro.model.User;
import ru.lyudofa.srpringcourse.gymbro.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/profile")
    public String getProfilePage(Model model, Principal principal) {
        return userService.findByUsername(principal.getName())
                .map(user -> {
                    model.addAttribute("user", user);
                    return "user/profile";
                })
                .orElse("redirect:/login"); // или страница с ошибкой
    }


//    @GetMapping
//    public String getAllUsers(Model model) {
//        List<User> users = userService.findAll();
//        model.addAttribute("users", users);
//        return "users/list";
//    }

    @GetMapping("/profile/edit")
    public String editProfileForm(Model model, Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping("/profile/edit")
    public String updateProfile(@ModelAttribute("user") User formUser, Principal principal) {
        userService.updateProfile(principal.getName(), formUser);
        return "redirect:/users/profile";
    }
}
