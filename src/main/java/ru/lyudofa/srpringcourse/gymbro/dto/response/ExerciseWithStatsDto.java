package ru.lyudofa.srpringcourse.gymbro.dto.response;

import ru.lyudofa.srpringcourse.gymbro.model.Exercise;

import java.time.LocalDateTime;

public record ExerciseWithStatsDto(
        Long id,
        String name,
        String muscleGroup,
        int totalWorkouts,
        LocalDateTime lastUsed
) {
//    public static ExerciseWithStatsDto fromEntity(Exercise exercise, int totalWorkouts, LocalDateTime lastUsed) {
//        return new ExerciseWithStatsDto(
//                exercise.getId(),
//                exercise.getName(),
//                exercise.getMuscleGroup(),
//                totalWorkouts,
//                lastUsed
//        );
//    }
}