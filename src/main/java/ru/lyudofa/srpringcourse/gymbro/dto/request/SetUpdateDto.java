package ru.lyudofa.srpringcourse.gymbro.dto.request;

import jakarta.validation.constraints.*;

public record SetUpdateDto(
        @Positive
        Long setNumber,

        @Positive
        Long reps,

        @PositiveOrZero
        @Digits(integer = 3, fraction = 2)
        Double weightKg,

        @Min(1)
        @Max(10)
        @Digits(integer = 1, fraction = 1)
        Double rpe,

        @Size(max = 1000)
        String notes
) {}