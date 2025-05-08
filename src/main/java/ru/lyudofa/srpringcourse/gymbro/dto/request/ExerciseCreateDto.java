package ru.lyudofa.srpringcourse.gymbro.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ExerciseCreateDto(
        @NotBlank(message = "Название обязательно")
        @Size(min = 2, max = 100, message = "Максимум 100 символов")
        String name,

        @Size(max = 500, message = "Максимум 500 символов")
        String description,

        @Size(max = 50, message = "Максимум 50 символов")
        String muscleGroup,

        @Size(max = 50, message = "Максимум 50 символов")
        String equipment
) {
}
