package com.toae.auth_player.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegistrationRequest(
        @NotBlank(message = "Username is mandatory")
        String username,
        @NotBlank(message = "Password is mandatory")
        String password, // Ensure it is always hashed.
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email should be valid")
        String email,
        // Evaluate possibility of creating a Phone Class
        @NotBlank(message = "Phone number is mandatory")
        String phoneNumber,
        // Evaluate possibility of creating an Address Class
        String address
) {
}
