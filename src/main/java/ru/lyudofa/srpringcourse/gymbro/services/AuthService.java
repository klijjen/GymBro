package ru.lyudofa.srpringcourse.gymbro.services;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import ru.lyudofa.srpringcourse.gymbro.model.User;
import ru.lyudofa.srpringcourse.gymbro.utils.PasswordUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthService {
    private final Map<String, String> sessions = new HashMap<>();
    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public String login(String username, String inputPassword) {
        User user = userService.findByUsername(username).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        if (!PasswordUtils.checkPassword(inputPassword, user.getPasswordHash())) {
            throw new RuntimeException("Неверный пароль");
        }
        String token = UUID.randomUUID().toString();
        sessions.put(token, username);
        return token;
    }

    public boolean isTokenValid(String token) {
        return sessions.containsKey(token);
    }

    public String getUsernameByToken(String token) {
        return sessions.get(token);
    }
}
