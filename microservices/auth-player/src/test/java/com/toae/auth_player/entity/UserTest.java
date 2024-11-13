package com.toae.auth_player.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id("12345")
                .username("testUser")
                .password("hashedPassword")
                .email("test@example.com")
                .phoneNumber("123456789")
                .address("123 Main St, City, Country")
                .build();
    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    void getId() {
        assertEquals("12345", user.getId());
    }

    @Test
    void getUsername() {
        assertEquals("testUser", user.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("hashedPassword", user.getPassword());
    }

    @Test
    void getEmail() {
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void getPhoneNumber() {
        assertEquals("123456789", user.getPhoneNumber());
    }

    @Test
    void getAddress() {
        assertEquals("123 Main St, City, Country", user.getAddress());
    }

    @Test
    void setId() {
        user.setId("67890");
        assertEquals("67890", user.getId());
    }

    @Test
    void setUsername() {
        user.setUsername("newUser");
        assertEquals("newUser", user.getUsername());
    }

    @Test
    void setPassword() {
        user.setPassword("newHashedPassword");
        assertEquals("newHashedPassword", user.getPassword());
    }

    @Test
    void setEmail() {
        user.setEmail("new@example.com");
        assertEquals("new@example.com", user.getEmail());
    }

    @Test
    void setPhoneNumber() {
        user.setPhoneNumber("987654321");
        assertEquals("987654321", user.getPhoneNumber());
    }

    @Test
    void setAddress() {
        user.setAddress("456 Another St, City, Country");
        assertEquals("456 Another St, City, Country", user.getAddress());
    }

    @Test
    void testToString() {
        String expected = "User(id=12345, username=testUser, password=hashedPassword, email=test@example.com, phoneNumber=123456789, address=123 Main St, City, Country)";
        assertEquals(expected, user.toString());
    }

    @Test
    void builder() {
        User newUser = User.builder()
                .id("54321")
                .username("builderUser")
                .password("builderHashedPassword")
                .email("builder@example.com")
                .phoneNumber("321654987")
                .address("789 Another St, City, Country")
                .build();

        assertNotNull(newUser);
        assertEquals("54321", newUser.getId());
        assertEquals("builderUser", newUser.getUsername());
        assertEquals("builderHashedPassword", newUser.getPassword());
        assertEquals("builder@example.com", newUser.getEmail());
        assertEquals("321654987", newUser.getPhoneNumber());
        assertEquals("789 Another St, City, Country", newUser.getAddress());
    }
}
