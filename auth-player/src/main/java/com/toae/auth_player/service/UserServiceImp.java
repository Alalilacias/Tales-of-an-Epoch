package com.toae.auth_player.service;

import com.toae.auth_player.dto.UserDto;
import com.toae.auth_player.dto.request.UserRegistrationRequest;
import com.toae.auth_player.entity.User;
import com.toae.auth_player.repository.UserRepository;
import com.toae.auth_player.service.interfaces.UserService;
import com.toae.auth_player.util.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // TODO: Evaluate better possibility of returning DTO with password and having front automatically login user upon register.
    @Override
    public Mono<UserDto> registerUser(UserRegistrationRequest registrationRequest) {
        User user = User
                .builder()
                .username(registrationRequest.username())
                .password(passwordEncoder.encode(registrationRequest.password()))
                .email(registrationRequest.email())
                .address(registrationRequest.address())
                .phoneNumber(registrationRequest.phoneNumber())
                .build();

        return userRepository.save(user).map(UserMapper::toDto);
    }
}
