package com.toae.auth_player.service.interfaces;

import com.toae.auth_player.dto.UserDto;
import com.toae.auth_player.dto.request.UserRegistrationRequest;
import com.toae.auth_player.entity.User;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserDto> registerUser(UserRegistrationRequest registrationRequest);
}
