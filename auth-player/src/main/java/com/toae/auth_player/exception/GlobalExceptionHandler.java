package com.toae.auth_player.exception;

import com.toae.auth_player.exception.auth.InvalidCredentialsException;
import com.toae.auth_player.exception.auth.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // User exceptions.
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Mono<ResponseEntity<String>> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()));
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Mono<ResponseEntity<String>> handleInvalidCredentialsException(InvalidCredentialsException e){
            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage()));
    }
}

