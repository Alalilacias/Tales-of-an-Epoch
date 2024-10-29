package com.toae.auth_player.service.interfaces;

import com.toae.auth_player.entity.User;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<User> registerUser(User user);
}
