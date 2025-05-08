package ru.lyudofa.srpringcourse.gymbro.dto.request;

import jakarta.validation.constraints.Size;

public record ExerciseUpdateDto(
        @Size(max = 100, message = "Максимум 100 символов")
        String name,

        @Size(max = 500, message = "Максимум 500 символов")
        String description,

        @Size(max = 50, message = "Максимум 50 символов")
        String muscleGroup,

        @Size(max = 50, message = "Максимум 50 символов")
        String equipment
) {}