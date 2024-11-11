package com.toae.auth_player.security;

import com.toae.auth_player.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class CustomUserDetailsTest {

    private CustomUserDetails customUserDetails;

    @BeforeEach
    void setUp() {
        User user = new User("1", "testUser", "hashedPassword", "test@example.com", "123456789", "123 Main St");
        customUserDetails = new CustomUserDetails(user);
    }

    @Test
    void getAuthorities() {
        Collection<? extends GrantedAuthority> authorities = customUserDetails.getAuthorities();

        assertNotNull(authorities, "Authorities should not be null");
        assertTrue(authorities.isEmpty(), "Authorities should be empty");
    }

    @Test
    void getPassword() {
        String password = customUserDetails.getPassword();

        assertEquals("hashedPassword", password, "Password does not match");
    }

    @Test
    void getUsername() {
        String username = customUserDetails.getUsername();

        assertEquals("test@example.com", username, "Username does not match");
    }
}
