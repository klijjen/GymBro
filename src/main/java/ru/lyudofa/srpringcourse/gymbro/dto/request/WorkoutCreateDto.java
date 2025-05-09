package ru.lyudofa.srpringcourse.gymbro.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

public record WorkoutCreateDto(
        @NotBlank @Size(max = 100)
        String name,
        @Size(max = 1000)
        String notes,
        @NotNull
        LocalDateTime startTime,
        List<WorkoutExerciseDto> exercises
) {}
