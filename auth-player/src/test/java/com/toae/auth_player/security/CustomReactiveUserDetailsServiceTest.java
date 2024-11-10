package com.toae.auth_player.security;

import com.toae.auth_player.entity.User;
import org.junit.jupiter.api.Test;

import com.toae.auth_player.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CustomReactiveUserDetailsServiceTest {

    private UserRepository userRepository;
    private CustomReactiveUserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userDetailsService = new CustomReactiveUserDetailsService(userRepository);
    }

    @Test
    void findByUsername() {
        // Arrange
        User user = new User("1", "testUser", "hashedPassword", "test@example.com", "123456789", "123 Main St");
        when(userRepository.findByEmail(anyString())).thenReturn(Mono.just(user));

        // Act
        Mono<UserDetails> result = userDetailsService.findByUsername("test@example.com");

        // Assert
        StepVerifier.create(result)
                .assertNext(userDetails -> {
                    assertTrue(userDetails instanceof CustomUserDetails, "UserDetails is not an instance of CustomUserDetails");
                    assertEquals("test@example.com", userDetails.getUsername(), "Username does not match");
                    assertEquals("hashedPassword", userDetails.getPassword(), "Password does not match");
                })
                .verifyComplete();
    }

}
