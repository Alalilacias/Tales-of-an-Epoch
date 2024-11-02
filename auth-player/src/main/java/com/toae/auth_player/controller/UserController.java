package com.toae.auth_player.controller;

import com.toae.auth_player.dto.UserDto;
import com.toae.auth_player.dto.request.UserRegistrationRequest;
import com.toae.auth_player.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Validated
@RequestMapping("/api/users")
@Tag(name = "User Controller",
        description = "Handles user registration and management")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<UserDto>> registerUser(@Validated @RequestBody UserRegistrationRequest request){
        return userService.registerUser(request).map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    public Flux<UserDto> getAllUsers(){
        return userService.getAll();
    }

}
