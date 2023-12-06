package com.dulino.desafio.config;

import com.dulino.desafio.entity.Role;
import com.dulino.desafio.repository.RoleRepository;
import com.dulino.desafio.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class InitializeRoles {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public InitializeRoles(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        List<Role> roles = Arrays.asList(new Role(null, "ROLE_ADMIN"), new Role(null, "ROLE_USER"));
        if (!roleRepository.existsByAuthorityIn(Arrays.asList("ROLE_ADMIN", "ROLE_USER"))) {
            roleRepository.saveAll(roles);
        }
    }
}
