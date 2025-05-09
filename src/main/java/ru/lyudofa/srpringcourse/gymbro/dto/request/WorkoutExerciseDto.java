package ru.lyudofa.srpringcourse.gymbro.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record WorkoutExerciseDto(
        @NotNull
        Long exerciseId,
        @Positive
        int orderIndex,
        @Size(max = 500)
        String notes,
        List<ExerciseSetDto> sets
) {}