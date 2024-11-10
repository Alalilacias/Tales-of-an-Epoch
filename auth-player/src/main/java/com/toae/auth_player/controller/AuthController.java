package com.toae.auth_player.controller;

import com.toae.auth_player.dto.UserDto;
import com.toae.auth_player.dto.request.UserLoginRequest;
import com.toae.auth_player.dto.request.UserRegistrationRequest;
import com.toae.auth_player.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Validated
@RequestMapping("api/auth")
@Tag(name = "Auth Controller", description = "Handles all user-related authorization operations.")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Register a new user",
            description = "Registers a new user with the provided information.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User registration data",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserRegistrationRequest.class),
                            examples = @ExampleObject(value = """
                                {
                                  "username": "newUser",
                                  "password": "securePassword",
                                  "email": "user@example.com",
                                  "phoneNumber": "123456789",
                                  "address": "123 Main St, City, Country"
                                }
                            """)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "User registered successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class),
                                    examples = @ExampleObject(value = """
                                        {
                                          "username": "newUser",
                                          "email": "user@example.com",
                                          "phoneNumber": "123456789"
                                        }
                                    """)
                            )),
                    @ApiResponse(responseCode = "409", description = "Conflict - Username or email already exists",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "{ \"error\": \"User with email user@example.com already exists.\" }")
                            ))
            }
    )
    @PostMapping("/register")
    public Mono<ResponseEntity<UserDto>> registerUser(@Validated @RequestBody UserRegistrationRequest request) {
        return userService.registerUser(request).map(ResponseEntity::ok);
    }

    @Operation(
            summary = "User login",
            description = "Authenticates a user with either username or email and password, returning a JWT token upon successful authentication.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User login credentials",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserLoginRequest.class),
                            examples = @ExampleObject(value = """
                                {
                                  "username": "existingUser",
                                  "email": "user@example.com",
                                  "password": "securePassword"
                                }
                            """)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "User logged in successfully, token generated",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "string"),
                                    examples = @ExampleObject(value = "{ \"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpPVC9...\" }")
                            )),
                    @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid credentials",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "{ \"error\": \"Bad credentials.\" }")
                            )),
                    @ApiResponse(responseCode = "409", description = "Conflict - User not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(value = "{ \"error\": \"User not found with identifiers: User: existingUser, Email: user@example.com\" }")
                            ))
            }
    )
    @PostMapping("/login")
    public Mono<ResponseEntity<String>> loginUser(@Validated @RequestBody UserLoginRequest request) {
        return userService.loginUser(request).map(ResponseEntity::ok);
    }
}

