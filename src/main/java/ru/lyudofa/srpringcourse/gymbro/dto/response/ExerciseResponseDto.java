package ru.lyudofa.srpringcourse.gymbro.dto.response;

import ru.lyudofa.srpringcourse.gymbro.model.Exercise;

import java.time.LocalDateTime;

public record ExerciseResponseDto(
    Long id,
    String name,
    String description,
    String muscleGroup,
    String equipment,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    UserBasicInfoDto createdBy
) {
    public static ExerciseResponseDto fromEntity(Exercise exercise) {
        return new ExerciseResponseDto(
                exercise.getId(),
                exercise.getName(),
                exercise.getDescription(),
                exercise.getMuscleGroup(),
                exercise.getEquipment(),
                exercise.getCreatedAt(),
                exercise.getUpdatedAt(),
                UserBasicInfoDto.fromEntity(exercise.getCreatedBy())
        );
    }
}