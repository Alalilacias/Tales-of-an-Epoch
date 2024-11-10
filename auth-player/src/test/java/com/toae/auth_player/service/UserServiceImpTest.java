package com.toae.auth_player.service;

import com.toae.auth_player.dto.request.UserLoginRequest;
import com.toae.auth_player.dto.request.UserRegistrationRequest;
import com.toae.auth_player.service.interfaces.AuthService;
import com.toae.auth_player.util.UserMapper;
import org.junit.jupiter.api.Test;

import com.toae.auth_player.dto.UserDto;
import com.toae.auth_player.entity.User;
import com.toae.auth_player.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthService authService;

    @InjectMocks
    private UserServiceImp userServiceImp;

    @Test
    void registerUser() {
        UserRegistrationRequest registrationRequest = new UserRegistrationRequest("newUser", "password123", "newuser@example.com", "123456789", "123 Main St");
        User user = User.builder()
                .username(registrationRequest.username())
                .password(passwordEncoder.encode(registrationRequest.password()))
                .email(registrationRequest.email())
                .phoneNumber(registrationRequest.phoneNumber())
                .address(registrationRequest.address())
                .build();
        UserDto userDto = UserMapper.toDto(user);

        when(userRepository.findByEmailOrUsername(registrationRequest.email(), registrationRequest.username()))
                .thenReturn(Mono.empty());
        when(userRepository.save(any(User.class)))
                .thenReturn(Mono.just(user));
        when(passwordEncoder.encode(registrationRequest.password()))
                .thenReturn("hashedPassword");

        StepVerifier.create(userServiceImp.registerUser(registrationRequest))
                .expectNext(userDto)
                .verifyComplete();

        verify(userRepository, times(1)).findByEmailOrUsername(registrationRequest.email(), registrationRequest.username());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void loginUser() {
        UserLoginRequest loginRequest = new UserLoginRequest("existingUser", "password123", "existinguser@example.com");
        User user = User.builder()
                .username(loginRequest.username())
                .password("hashedPassword")
                .email(loginRequest.email())
                .build();

        when(userRepository.findByEmailOrUsername(loginRequest.email(), loginRequest.username()))
                .thenReturn(Mono.just(user));
        when(passwordEncoder.matches(loginRequest.password(), user.getPassword()))
                .thenReturn(true);
        when(authService.generateToken(user.getUsername()))
                .thenReturn("mockToken");

        StepVerifier.create(userServiceImp.loginUser(loginRequest))
                .expectNext("mockToken")
                .verifyComplete();

        verify(userRepository, times(1)).findByEmailOrUsername(loginRequest.email(), loginRequest.username());
        verify(passwordEncoder, times(1)).matches(loginRequest.password(), user.getPassword());
        verify(authService, times(1)).generateToken(user.getUsername());
    }
}
