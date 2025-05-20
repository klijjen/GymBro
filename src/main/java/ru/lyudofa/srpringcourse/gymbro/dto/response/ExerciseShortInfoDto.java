package ru.lyudofa.srpringcourse.gymbro.dto.response;

import ru.lyudofa.srpringcourse.gymbro.model.Exercise;

public record ExerciseShortInfoDto(
        Long id,
        String name,
        String muscleGroup
) {
//    public static ExerciseShortInfoDto fromEntity(Exercise exercise) {
//        return new ExerciseShortInfoDto(
//                exercise.getId(),
//                exercise.getName(),
//                exercise.getMuscleGroup()
//        );
//    }
}