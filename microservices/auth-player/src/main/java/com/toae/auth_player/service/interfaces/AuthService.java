package com.toae.auth_player.service.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import reactor.core.publisher.Mono;

public interface AuthService {
    String generateToken(UserDetails userDetails);

    String generateToken(String subject);

    Mono<Void> invalidateToken(String token);

    Mono<Boolean> validateToken(String token, UserDetails userDetails);

    Mono<Boolean> validateResetToken(String token, String identifier);

    String getUsername(String token);
}
