package com.toae.auth_player.service;

import com.toae.auth_player.dto.UserDto;
import com.toae.auth_player.dto.request.UserRegistrationRequest;
import com.toae.auth_player.entity.User;
import com.toae.auth_player.exception.user.UserAlreadyExistsException;
import com.toae.auth_player.repository.UserRepository;
import com.toae.auth_player.service.interfaces.UserService;
import com.toae.auth_player.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<UserDto> registerUser(UserRegistrationRequest registrationRequest) {
        return userRepository.findByEmailOrUsername(registrationRequest.email(), registrationRequest.username())
                .flatMap(existingUser -> {
                    if (existingUser.getEmail().equals(registrationRequest.email())) {
                        return Mono.error(new UserAlreadyExistsException("User with email " + registrationRequest.email() + " already exists."));
                    } else if (existingUser.getUsername().equals(registrationRequest.username())) {
                        return Mono.error(new UserAlreadyExistsException("User with username " + registrationRequest.username() + " already exists."));
                    }

                    return userRepository.save(User
                                    .builder()
                                    .username(registrationRequest.username())
                                    .password(passwordEncoder.encode(registrationRequest.password())) // Hash the password
                                    .email(registrationRequest.email())
                                    .address(registrationRequest.address())
                                    .phoneNumber(registrationRequest.phoneNumber())
                                    .build())
                            .map(UserMapper::toDto);
                });
    }

    @Override
    public Flux<UserDto> getAll(){
        return userRepository.findAll().map(UserMapper::toDto);
    }
}
