package com.toae.auth_player.controller;

import com.toae.auth_player.dto.UserDto;
import com.toae.auth_player.dto.request.UserLoginRequest;
import com.toae.auth_player.dto.request.UserRegistrationRequest;
import com.toae.auth_player.service.interfaces.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SuppressWarnings("DataFlowIssue")
class AuthControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void registerUser() {
        UserRegistrationRequest request = new UserRegistrationRequest("newUser", "securePassword", "user@example.com", "123456789", "123 Main St, City, Country");
        UserDto responseDto = new UserDto("newUser", "user@example.com", "123456789");

        when(userService.registerUser(request)).thenReturn(Mono.just(responseDto));

        Mono<ResponseEntity<UserDto>> response = authController.registerUser(request);

        assertNotNull(response);
        assertEquals(responseDto, response.block().getBody());
        assertEquals(200, response.block().getStatusCode().value());

        verify(userService, times(1)).registerUser(request);
    }

    @Test
    void loginUser() {
        UserLoginRequest request = new UserLoginRequest("existingUser", "user@example.com", "securePassword");
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpVCJ...";

        when(userService.loginUser(request)).thenReturn(Mono.just(token));

        Mono<ResponseEntity<String>> response = authController.loginUser(request);

        assertNotNull(response);
        assertEquals(token, response.block().getBody());
        assertEquals(200, response.block().getStatusCode().value());

        verify(userService, times(1)).loginUser(request);
    }
}
