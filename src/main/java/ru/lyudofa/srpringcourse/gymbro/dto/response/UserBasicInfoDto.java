package ru.lyudofa.srpringcourse.gymbro.dto.response;

import ru.lyudofa.srpringcourse.gymbro.model.User;

public record UserBasicInfoDto(
        Long id,
        String username
) {
    public static UserBasicInfoDto fromEntity(User user) {
        return user != null
                ? new UserBasicInfoDto(user.getId(), user.getUsername())
                : null;
    }
}