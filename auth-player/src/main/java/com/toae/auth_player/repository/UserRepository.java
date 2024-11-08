package com.toae.auth_player.repository;

import com.toae.auth_player.entity.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    @Query("{ '$or': [ { 'email': ?0 }, { 'username': ?1 } ] }")
    Mono<User> findByEmailOrUsername(String email, String username);

    Mono<User> findByEmail(String email);
    Mono<User> findByUsername(String username);

}
