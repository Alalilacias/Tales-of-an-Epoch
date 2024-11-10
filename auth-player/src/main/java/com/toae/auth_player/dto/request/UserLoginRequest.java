package com.toae.auth_player.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserLoginRequest(
        @Schema(description = "Username for logging in") String username,
        @Schema(description = "Email for logging in") String email,
        @Schema(description = "Password for logging in") String password) {
}
