package com.toae.auth_player.exception.auth;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvalidCredentialsExceptionTest {

    @Test
    void testExceptionMessage() {
        String message = "Invalid credentials provided";
        InvalidCredentialsException exception = new InvalidCredentialsException(message);

        assertEquals(message, exception.getMessage());
    }
}
