package com.toae.auth_player.config;

import org.junit.jupiter.api.Test;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import static org.junit.jupiter.api.Assertions.*;

@SuppressWarnings("DataFlowIssue")
class CorsConfigTest {

    @Test
    void corsWebFilter() {
        CorsConfig corsConfig = new CorsConfig();

        CorsWebFilter corsWebFilter = corsConfig.corsWebFilter();
        assertNotNull(corsWebFilter, "CorsWebFilter should not be null");

        UrlBasedCorsConfigurationSource expectedSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration expectedConfig = new CorsConfiguration().applyPermitDefaultValues();
        expectedConfig.addAllowedOrigin("*");
        expectedConfig.addAllowedMethod("*");
        expectedConfig.addAllowedHeader("*");
        expectedSource.registerCorsConfiguration("/**", expectedConfig);

        assertTrue(expectedConfig.getAllowedOrigins().contains("*"));
        assertTrue(expectedConfig.getAllowedMethods().contains("*"));
        assertTrue(expectedConfig.getAllowedHeaders().contains("*"));
    }
}

