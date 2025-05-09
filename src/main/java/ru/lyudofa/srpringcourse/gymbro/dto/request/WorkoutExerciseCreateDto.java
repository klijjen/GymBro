package ru.lyudofa.srpringcourse.gymbro.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record WorkoutExerciseCreateDto(
        @NotNull(message = "ID упражнения обязательно")
        Long exerciseId,

        @NotNull(message = "Порядковый номер обязателен")
        @Positive(message = "Порядковый номер должен быть положительным")
        Long orderIndex,

        @Size(max = 500, message = "Максимум 500 символов")
        String notes,

        @Size(min = 1, message = "Должен быть хотя бы один подход")
        List<SetCreateDto> sets
) {}
