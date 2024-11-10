package com.toae.auth_player.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRegistrationRequestTest {

    private final UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest(
            "newUser",
            "securePassword",
            "user@example.com",
            "123456789",
            "123 Main St, City, Country"
    );

    @Test
    void username() {
        assertEquals("newUser", userRegistrationRequest.username());
    }

    @Test
    void password() {
        assertEquals("securePassword", userRegistrationRequest.password());
    }

    @Test
    void email() {
        assertEquals("user@example.com", userRegistrationRequest.email());
    }

    @Test
    void phoneNumber() {
        assertEquals("123456789", userRegistrationRequest.phoneNumber());
    }

    @Test
    void address() {
        assertEquals("123 Main St, City, Country", userRegistrationRequest.address());
    }
}
