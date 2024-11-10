package com.toae.auth_player.repository;

import com.toae.auth_player.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataMongoTest
@Testcontainers
class UserRepositoryTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:6.0");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = new User("1", "testUser", "hashedPassword", "test@example.com", "123456789", "123 Main St");
        userRepository.save(user).block();
    }

    @Test
    void findByEmailOrUsername() {
        Mono<User> result = userRepository.findByEmailOrUsername("test@example.com", "testUser");

        StepVerifier.create(result)
                .expectNextMatches(user -> "testUser".equals(user.getUsername()) && "test@example.com".equals(user.getEmail()))
                .verifyComplete();
    }

    @Test
    void findByEmail() {
        Mono<User> result = userRepository.findByEmail("test@example.com");

        StepVerifier.create(result)
                .expectNextMatches(user -> "test@example.com".equals(user.getEmail()))
                .verifyComplete();
    }
}

