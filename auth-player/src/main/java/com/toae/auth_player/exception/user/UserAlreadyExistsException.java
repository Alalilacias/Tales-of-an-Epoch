package com.toae.auth_player.exception.user;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message + "already exists.");
    }
}
