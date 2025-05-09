package ru.lyudofa.srpringcourse.gymbro.dto.request;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record WorkoutExerciseUpdateDto(
        @Positive
        Long orderIndex,

        @Size(max = 500)
        String notes
) {}