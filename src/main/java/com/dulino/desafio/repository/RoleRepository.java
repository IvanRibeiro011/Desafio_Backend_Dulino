package com.dulino.desafio.repository;

import com.dulino.desafio.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role,String> {
    Optional<Role> findByAuthority(String name);
    boolean existsByAuthorityIn(List<String> authorities);
}
