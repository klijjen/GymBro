package ru.lyudofa.srpringcourse.gymbro.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreateDto(
        @NotBlank
        @Size(min = 4, max = 50, message = "Имя должно быть от 4 до 50 символов")
        String name,

        @Email(message = "Некорректный формат email")
        String email,

        @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$",
                message = "Пароль должен содержать 8+ символов латиницы, цифру и заглавную букву")
        String password
) { }
