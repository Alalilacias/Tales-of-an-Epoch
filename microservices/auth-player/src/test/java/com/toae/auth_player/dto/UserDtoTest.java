package com.toae.auth_player.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {

    private final UserDto userDto = new UserDto("testUser", "test@example.com", "123456789");

    @Test
    void username() {
        assertEquals("testUser", userDto.username());
    }

    @Test
    void email() {
        assertEquals("test@example.com", userDto.email());
    }

    @Test
    void phoneNumber() {
        assertEquals("123456789", userDto.phoneNumber());
    }
}
