package ru.lyudofa.srpringcourse.gymbro.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void testBuilder() {
        User user = User.builder()
                .username("testuser")
                .email("test@example.com")
                .passwordHash("hash")
                .build();

        System.out.println(user.getUsername());
    }
}