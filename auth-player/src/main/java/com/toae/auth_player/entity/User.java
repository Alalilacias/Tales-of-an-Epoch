package com.toae.auth_player.entity;

import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class User {

    @MongoId
    private String id;

    private String username;

    private String password; // Ensure it is always hashed.

    @Email(message = "Email should be valid")
    private String email;

    // Evaluate possibility of creating a Phone Class
    private String phoneNumber;

    // Evaluate possibility of creating an Address Class
    private String address;

    // Additional fields can be added as needed
    // Most Likely, private String AccountId;
}
