package ru.lyudofa.srpringcourse.gymbro.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.lyudofa.srpringcourse.gymbro.model.User;
import ru.lyudofa.srpringcourse.gymbro.services.AuthService;
import ru.lyudofa.srpringcourse.gymbro.services.UserService;

import java.util.List;
import java.util.Map;

//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/users")
//public class UserController {
//
//    private final UserService userService;
//    private final AuthService authService;
//
//    @GetMapping
//    public ResponseEntity<List<User>> getAllUsers() {
//        return ResponseEntity.ok(userService.findAll());
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
//        }
//        try {
//            User registeredUser = userService.registerUser(user);
//            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
//        }
//        catch (Exception e) {
//            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
//        }
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
//        try {
//            String token = authService.login(
//                    credentials.get("username"),
//                    credentials.get("password")
//            );
//            return ResponseEntity.ok(Map.of("token", token));
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
//        }
//    }
//
//    @GetMapping("/profile")
//    public ResponseEntity<?> getProfile(@RequestHeader("Authorization") String token) {
//        if (token.startsWith("Bearer ")) {
//            token = token.substring(7);
//        }
//        if (!authService.isTokenValid(token)) {
//            return ResponseEntity.status(401).body(Map.of("error", "Недействительный токен"));
//        }
//        String username = authService.getUsernameByToken(token);
//        User user = userService.findByUsername(username).orElseThrow();
//        return ResponseEntity.ok(user);
//    }
//
//    @GetMapping("/test")
//    public ResponseEntity<String> test() {
//        System.out.println("Контроллер вызван!");
//        return ResponseEntity.ok("OK");
//    }
//
//
//}


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credentials) {
        try {
            String token = authService.login(
                    credentials.get("username"),
                    credentials.get("password")
            );
            return ResponseEntity.ok(Map.of("token", token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        }
    }
    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        System.out.println("Контроллер вызван!");
        return ResponseEntity.ok("OK");
    }
}
