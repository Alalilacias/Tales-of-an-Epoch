package com.toae.auth_player.exception;

import com.toae.auth_player.exception.auth.InvalidCredentialsException;
import com.toae.auth_player.exception.auth.UserAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("DataFlowIssue")
class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleUserAlreadyExistsException() {
        UserAlreadyExistsException exception = new UserAlreadyExistsException("User already exists.");

        Mono<ResponseEntity<String>> response = globalExceptionHandler.handleUserAlreadyExistsException(exception);

        assertNotNull(response);
        ResponseEntity<String> actualResponse = response.block();
        assertEquals(HttpStatus.CONFLICT, actualResponse.getStatusCode());
        assertEquals("User already exists.", actualResponse.getBody());
    }

    @Test
    void handleInvalidCredentialsException() {
        InvalidCredentialsException exception = new InvalidCredentialsException("Invalid credentials provided");

        Mono<ResponseEntity<String>> response = globalExceptionHandler.handleInvalidCredentialsException(exception);

        assertNotNull(response);
        ResponseEntity<String> actualResponse = response.block();
        assertEquals(HttpStatus.UNAUTHORIZED, actualResponse.getStatusCode());
        assertEquals("Invalid credentials provided", actualResponse.getBody());
    }

    @Test
    void handleUserAlreadyExistsExceptionWithNullMessage() {
        UserAlreadyExistsException exception = new UserAlreadyExistsException(null);

        Mono<ResponseEntity<String>> response = globalExceptionHandler.handleUserAlreadyExistsException(exception);

        assertNotNull(response);
        ResponseEntity<String> actualResponse = response.block();
        assertEquals(HttpStatus.CONFLICT, actualResponse.getStatusCode());
        assertNull(actualResponse.getBody());
    }

    @Test
    void handleInvalidCredentialsExceptionWithNullMessage() {
        InvalidCredentialsException exception = new InvalidCredentialsException(null);

        Mono<ResponseEntity<String>> response = globalExceptionHandler.handleInvalidCredentialsException(exception);

        assertNotNull(response);
        ResponseEntity<String> actualResponse = response.block();
        assertEquals(HttpStatus.UNAUTHORIZED, actualResponse.getStatusCode());
        assertNull(actualResponse.getBody());
    }
}

