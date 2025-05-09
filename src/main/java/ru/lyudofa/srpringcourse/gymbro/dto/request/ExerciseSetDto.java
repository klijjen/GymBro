package ru.lyudofa.srpringcourse.gymbro.dto.request;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record ExerciseSetDto(
        @Positive
        int setNumber,
        @Positive
        int reps,
        @PositiveOrZero
        Double weightKg,
        @Size(max = 200)
        String notes
) {}
