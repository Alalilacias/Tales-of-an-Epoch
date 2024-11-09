package com.toae.auth_player.service;

import com.toae.auth_player.dto.UserDto;
import com.toae.auth_player.dto.request.UserLoginRequest;
import com.toae.auth_player.dto.request.UserRegistrationRequest;
import com.toae.auth_player.entity.User;
import com.toae.auth_player.exception.auth.InvalidCredentialsException;
import com.toae.auth_player.exception.auth.UserAlreadyExistsException;
import com.toae.auth_player.repository.UserRepository;
import com.toae.auth_player.service.interfaces.AuthService;
import com.toae.auth_player.service.interfaces.UserService;
import com.toae.auth_player.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    @Autowired
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthService authService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
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
                    return Mono.empty();
                })
                .switchIfEmpty(
                        userRepository.save(User.builder()
                                        .username(registrationRequest.username())
                                        .password(passwordEncoder.encode(registrationRequest.password())) // Hash the password
                                        .email(registrationRequest.email())
                                        .address(registrationRequest.address())
                                        .phoneNumber(registrationRequest.phoneNumber())
                                        .build())
                                .map(UserMapper::toDto)
                ).cast(UserDto.class);
    }

    @Override
    public Mono<String> loginUser(UserLoginRequest loginRequest){
        return userRepository.findByEmailOrUsername(loginRequest.email(), loginRequest.username())
                .switchIfEmpty(Mono.error(
                        new UserAlreadyExistsException("User not found with identifiers:" +
                                "\nUser: " + loginRequest.email() +
                                "\nEmail: " + loginRequest.username())))
                .flatMap(
                        user -> {
                            if (passwordEncoder.matches(loginRequest.password(), user.getPassword())){
                                return Mono.just(authService.generateToken(user.getUsername()));
                            } else {
                                return Mono.error(new InvalidCredentialsException("Bad credentials."));
                            }
                        }
                );
    }
}
