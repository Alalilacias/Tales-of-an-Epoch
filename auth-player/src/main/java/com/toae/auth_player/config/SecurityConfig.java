package com.toae.auth_player.config;

import com.toae.auth_player.security.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private static final String[] AUTHORIZED_REQUESTS = {
            "/api/users/register", "/api/users",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/actuator/**"
    };

    private final TokenFilter tokenFilter;

    @Autowired
    public SecurityConfig(TokenFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(AUTHORIZED_REQUESTS).permitAll()
                        .anyExchange().authenticated()
                )
                .addFilterBefore(tokenFilter, SecurityWebFiltersOrder.AUTHENTICATION) // Add TokenFilter
                .build();
    }
}