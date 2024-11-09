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

    private String username;  // Unique username for each user

    private String password;  // Ensure it is always hashed before saving

    @Email(message = "Email should be valid")
    private String email;

    private String phoneNumber;  // Format validation may be added if using a Phone class

    private String address;  // Consider an Address class if more fields are needed

    // Consider adding more variables.
    // Future: private String accountId; // Unique account ID for user account tracking
}

