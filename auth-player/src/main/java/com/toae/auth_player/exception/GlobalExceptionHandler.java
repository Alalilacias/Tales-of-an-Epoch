package com.toae.auth_player.exception;

import com.toae.auth_player.exception.user.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // User exceptions.
    // Todo: update global exception handler so it handles them.
    @ExceptionHandler(UserAlreadyExistsException.class)
    public Mono<ResponseEntity<ProblemDetail>> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Conflict");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setInstance(URI.create("/api/users/register"));

        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetail));
    }

}

