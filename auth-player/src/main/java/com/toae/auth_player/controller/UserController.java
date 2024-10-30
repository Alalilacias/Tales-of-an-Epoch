package com.toae.auth_player.controller;

import com.toae.auth_player.dto.UserDto;
import com.toae.auth_player.dto.request.UserRegistrationRequest;
import com.toae.auth_player.service.interfaces.UserService;
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
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Consider possibility of creating a UserRegistrationResponse, depending on the needs of the service.
    @PostMapping("/register")
    public Mono<ResponseEntity<UserDto>> registerUser(@Validated @RequestBody UserRegistrationRequest request){
        return userService.registerUser(request).map(ResponseEntity::ok);
    }

}
