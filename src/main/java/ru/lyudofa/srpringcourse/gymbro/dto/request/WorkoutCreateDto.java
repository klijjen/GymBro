package ru.lyudofa.srpringcourse.gymbro.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.List;

public record WorkoutCreateDto(
        @NotBlank
        @Size(max = 100, message = "Название тренировки должно быть меньше 100 символов")
        String name,
        @Size(max = 1000, message = "Заметка не может содержать больше 1000 символов")
        String notes,
        @NotNull
        LocalDateTime startTime,
        List<WorkoutExerciseCreateDto> exercises
) {}
