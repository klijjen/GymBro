package ru.lyudofa.srpringcourse.gymbro.dto.response;

import ru.lyudofa.srpringcourse.gymbro.model.WorkoutExercise;

import java.time.LocalDateTime;
import java.util.List;

public record WorkoutExerciseResponseDto(
        Long id,
        Long orderIndex,
        String notes,
        LocalDateTime createdAt,
        ExerciseShortInfoDto exercise,
        List<SetResponseDto> sets
) {
    public static WorkoutExerciseResponseDto fromEntity(WorkoutExercise we) {
        return new WorkoutExerciseResponseDto(
                we.getId(),
                we.getOrderIndex(),
                we.getNotes(),
                we.getCreatedAt(),
                ExerciseShortInfoDto.fromEntity(we.getExercise()),
                we.getSets().stream()
                        .map(SetResponseDto::fromEntity)
                        .toList()
        );
    }
}