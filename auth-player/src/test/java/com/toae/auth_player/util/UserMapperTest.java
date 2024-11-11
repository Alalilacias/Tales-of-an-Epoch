package com.toae.auth_player.util;

import org.junit.jupiter.api.Test;

import com.toae.auth_player.dto.UserDto;
import com.toae.auth_player.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserMapperTest {

    @Test
    void toDto() {
        User user = User.builder()
                .username("testUser")
                .email("testUser@example.com")
                .phoneNumber("123456789")
                .build();

        UserDto userDto = UserMapper.toDto(user);

        assertNotNull(userDto);
        assertEquals(user.getUsername(), userDto.username());
        assertEquals(user.getEmail(), userDto.email());
        assertEquals(user.getPhoneNumber(), userDto.phoneNumber());
    }
}
