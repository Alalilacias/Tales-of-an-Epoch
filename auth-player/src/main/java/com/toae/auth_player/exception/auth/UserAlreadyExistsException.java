package com.toae.auth_player.exception.auth;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message + "already exists.");
    }
}
