package ru.lyudofa.srpringcourse.gymbro.dto.response;

import ru.lyudofa.srpringcourse.gymbro.model.Workout;

import java.time.LocalDateTime;
import java.util.List;

public record WorkoutResponseDto(
        Long id,
        String name,
        String notes,
        LocalDateTime startTime,
        LocalDateTime endTime,
        LocalDateTime createdAt,
        boolean completed,
        UserBasicInfoDto user,
        List<WorkoutExerciseResponseDto> exercises
) {
//    public static WorkoutResponseDto fromEntity(Workout workout) {
//        return new WorkoutResponseDto(
//                workout.getId(),
//                workout.getName(),
//                workout.getNotes(),
//                workout.getStartTime(),
//                workout.getEndTime(),
//                workout.getCreatedAt(),
//                workout.getEndTime() != null,
//                UserBasicInfoDto.fromEntity(workout.getUser()),
//                workout.getExercises().stream()
//                        .map(WorkoutExerciseResponseDto::fromEntity)
//                        .toList()
//        );
//    }
}