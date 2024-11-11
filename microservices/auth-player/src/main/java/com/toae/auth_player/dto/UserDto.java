package com.toae.auth_player.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UserDto(
        @Schema(description = "Username of the registered user") String username,
        @Schema(description = "Email address of the registered user") String email,
        @Schema(description = "Phone number of the registered user") String phoneNumber) {
}
