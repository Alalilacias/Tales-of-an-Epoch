package com.toae.auth_player.util;

import com.toae.auth_player.dto.UserDto;
import com.toae.auth_player.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDto toDto(User user){
        return new UserDto(
                user.getUsername(),
                user.getEmail(),
                user.getPhoneNumber()
        );
    }

}
