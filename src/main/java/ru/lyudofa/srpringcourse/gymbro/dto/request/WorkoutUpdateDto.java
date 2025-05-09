package ru.lyudofa.srpringcourse.gymbro.dto.request;

import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record WorkoutUpdateDto(
        @Size(max = 100)
        String name,
        @Size(max = 1000)
        String notes,
        LocalDateTime endTime,
        Boolean completed
) {}