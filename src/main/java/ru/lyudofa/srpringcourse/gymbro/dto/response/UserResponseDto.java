package ru.lyudofa.srpringcourse.gymbro.dto.response;

import ru.lyudofa.srpringcourse.gymbro.model.User;

import java.time.LocalDateTime;

public record UserResponseDto(
        Long id,
        String username,
        String email,
        LocalDateTime createdAt,
        Integer workoutsCount
) {
    public UserResponseDto fromEntity(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getWorkouts().size()
        );
    }
}
