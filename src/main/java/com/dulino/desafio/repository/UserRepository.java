package com.dulino.desafio.repository;

import com.dulino.desafio.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
