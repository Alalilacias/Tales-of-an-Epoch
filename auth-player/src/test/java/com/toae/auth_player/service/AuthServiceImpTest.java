package com.toae.auth_player.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.lang.reflect.Field;
import java.util.HashMap;

@ExtendWith(MockitoExtension.class)
class AuthServiceImpTest {

    @InjectMocks
    private AuthServiceImp authServiceImp;

    @BeforeEach
    void setUp() throws Exception {
        setPrivateField(authServiceImp, "secretKey", "97130e3800105359885cf5f4ec8d73cb95374f742c7ce77eb968e0237cdc7087"); // Provide a valid base64 key
        setPrivateField(authServiceImp, "expirationTime", 3600000L); // Example: 1 hour
        authServiceImp.init(); // Manually initialize the signing key
    }

    private void setPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }

    @Test
    void generateToken() {
        UserDetails userDetails = User.withUsername("testUser")
                .password("password")
                .authorities("ROLE_USER")
                .build();

        String token = authServiceImp.generateToken(userDetails);

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void testGenerateToken() {
        UserDetails userDetails = User.withUsername("testUser")
                .password("password")
                .authorities("ROLE_USER")
                .build();

        String token = authServiceImp.generateToken(new HashMap<>(), userDetails);

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void testGenerateToken1() {
        String token = authServiceImp.generateToken("testSubject");

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void invalidateToken() {
        String token = authServiceImp.generateToken("testSubject");

        Mono<Void> result = authServiceImp.invalidateToken(token);

        StepVerifier.create(result)
                .verifyComplete();

        assertEquals(Boolean.TRUE, authServiceImp.isTokenBlacklisted(token).block());
    }

    @Test
    void isTokenBlacklisted() {
        String token = authServiceImp.generateToken("testSubject");
        authServiceImp.invalidateToken(token).block();

        StepVerifier.create(authServiceImp.isTokenBlacklisted(token))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void validateToken() {
        UserDetails userDetails = User.withUsername("testUser")
                .password("password")
                .authorities("ROLE_USER")
                .build();

        String token = authServiceImp.generateToken(userDetails);

        StepVerifier.create(authServiceImp.validateToken(token, userDetails))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void validateResetToken() {
        String token = authServiceImp.generateToken("testSubject");

        StepVerifier.create(authServiceImp.validateResetToken(token, "testSubject"))
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void getUsername() {
        String token = authServiceImp.generateToken("testSubject");

        String username = authServiceImp.getUsername(token);

        assertEquals("testSubject", username);
    }
}
