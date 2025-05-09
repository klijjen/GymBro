package ru.lyudofa.srpringcourse.gymbro.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record WorkoutExerciseCreateDto(
        @NotNull
        Long exerciseId,
        @Positive
        int orderIndex,
        @Size(max = 1000, message = "Заметка не может содержать больше 1000 символов")
        String notes,
        List<SetCreateDto> sets
) {}