package com.toae.auth_player.exception.auth;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserAlreadyExistsExceptionTest {

    @Test
    void testExceptionMessage() {
        String message = "User already exists.";
        UserAlreadyExistsException exception = new UserAlreadyExistsException(message);

        assertEquals(message, exception.getMessage());
    }
}
