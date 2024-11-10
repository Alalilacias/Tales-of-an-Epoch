package com.toae.auth_player.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRegistrationRequest(
        @Schema(description = "Username for the new user account")
        @NotBlank(message = "Username is mandatory")
        String username,

        @Schema(description = "Password for the new user account")
        @NotBlank(message = "Password is mandatory")
        String password,

        @Schema(description = "Email address for the new user account")
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email should be valid")
        String email,

        @Schema(description = "Phone number for the new user account")
        @NotBlank(message = "Phone number is mandatory")
        String phoneNumber,

        @Schema(description = "Address for the new user account")
        String address
) {
}
