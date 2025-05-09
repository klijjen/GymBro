package ru.lyudofa.srpringcourse.gymbro.dto.request;

import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record WorkoutUpdateDto(
        @Size(max = 100, message = "Название тренировки должно быть меньше 100 символов")
        String name,
        @Size(max = 1000, message = "Заметка не может содержать больше 1000 символов")
        String notes,
        LocalDateTime endTime,
        Boolean completed
) {}