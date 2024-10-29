package com.toae.auth_player.service;

import com.toae.auth_player.service.interfaces.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;

@Service
public class AuthServiceImp implements AuthService {

    @Value("${spring.security.auth.secret}")
    private String secretKey;

    @Value("${spring.security.auth.expiration}")
    private long expirationTime;

    private final Set<String> blacklistedTokens = new HashSet<>();
    private SecretKey signingKey;

    @PostConstruct
    private void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(signingKey)
                .compact();
    }

    @Override
    public String generateToken(String subject) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .claims(new HashMap<>())
                .subject(subject)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(signingKey)
                .compact();
    }

    @Override
    public Mono<Void> invalidateToken(String token) {
        blacklistedTokens.add(token);
        return Mono.empty();
    }

    public Mono<Boolean> isTokenBlacklisted(String token) {
        return Mono.just(blacklistedTokens.contains(token));
    }

    @Override
    public Mono<Boolean> validateToken(String token, UserDetails userDetails) {
        return Mono.just(getUsername(token))
                .flatMap(username -> {
                    boolean isValid = username.equals(userDetails.getUsername())
                            && isTokenExpired(token);
                    return isTokenBlacklisted(token)
                            .map(isBlacklisted -> isValid && !isBlacklisted);
                });
    }

    @Override
    public Mono<Boolean> validateResetToken(String token, String identifier) {
        try {
            return Mono.just(getUsername(token).equals(identifier) && isTokenExpired(token));
        } catch (Exception e) {
            return Mono.just(false);
        }
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    @Override
    public String getUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }

    private <T> T getClaim(String token, Function<Claims, T> claimsFunction) {
        final Claims claims = getAllClaims(token);
        return claimsFunction.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
