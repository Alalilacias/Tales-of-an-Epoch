package com.toae.auth_player.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserLoginRequestTest {

    private final UserLoginRequest userLoginRequest = new UserLoginRequest(
            "testUser",
            "test@example.com",
            "securePassword"
    );

    @Test
    void username() {
        assertEquals("testUser", userLoginRequest.username());
    }

    @Test
    void email() {
        assertEquals("test@example.com", userLoginRequest.email());
    }

    @Test
    void password() {
        assertEquals("securePassword", userLoginRequest.password());
    }
}