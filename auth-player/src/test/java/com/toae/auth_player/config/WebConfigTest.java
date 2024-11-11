package com.toae.auth_player.config;

import org.junit.jupiter.api.Test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WebConfigTest {

    @Test
    void passwordEncoder() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class)) {
            PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);

            assertNotNull(passwordEncoder, "PasswordEncoder bean should not be null");
        }
    }
}
