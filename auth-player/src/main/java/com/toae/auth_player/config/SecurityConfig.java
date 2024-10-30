package com.toae.auth_player.config;

import com.toae.auth_player.security.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private static final String[] AUTHORIZED_REQUESTS = {
            "/api/users/register",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };

    private final TokenFilter tokenFilter;

    @Autowired
    public SecurityConfig(TokenFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(
                        exchanges -> exchanges
                                .pathMatchers(AUTHORIZED_REQUESTS).permitAll()
                                .anyExchange().authenticated())
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .addFilterBefore(tokenFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}