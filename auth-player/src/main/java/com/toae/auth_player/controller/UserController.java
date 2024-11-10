package com.toae.auth_player.controller;

import com.toae.auth_player.service.interfaces.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/users")
@Tag(name = "User Controller",
        description = "")
public class UserController {
}
