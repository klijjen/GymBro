package ru.lyudofa.srpringcourse.gymbro.dto.response;

import ru.lyudofa.srpringcourse.gymbro.model.Set;

import java.time.LocalDateTime;

public record SetResponseDto(
        Long id,
        Long setNumber,
        Long reps,
        Double weightKg,
        Double rpe,
        String notes,
        LocalDateTime createdAt
) {
    public static SetResponseDto fromEntity(Set set) {
        return new SetResponseDto(
                set.getId(),
                set.getSetNumber(),
                set.getReps(),
                set.getWeightKg(),
                set.getRpe(),
                set.getNotes(),
                set.getCreatedAt()
        );
    }
}