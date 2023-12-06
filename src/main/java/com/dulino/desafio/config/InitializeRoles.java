package com.dulino.desafio.config;

import com.dulino.desafio.entity.Role;
import com.dulino.desafio.entity.User;
import com.dulino.desafio.repository.RoleRepository;
import com.dulino.desafio.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@Configuration
public class InitializeRoles {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    public InitializeRoles(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }

    @PostConstruct
    public void init() {
        createRoles();
        createUsers();
    }

    private void createRoles() {
        List<Role> roles = Arrays.asList(new Role(null, "ROLE_USER"), new Role(null, "ROLE_ADMIN"));

        if (!roleRepository.existsByAuthorityIn(Arrays.asList("ROLE_ADMIN", "ROLE_USER"))) {
            roleRepository.saveAll(roles);
        }
    }

    private void createUsers() {
        if (!userRepository.existsByEmailIn(Arrays.asList("admin@hotmail.com", "user@gmail.com"))) {
            User admin = createUser("admin", "admin@gmail.com", "123456", Arrays.asList("ROLE_ADMIN", "ROLE_USER"));
            User user = createUser("user", "user@gmail.com", "123456", Arrays.asList("ROLE_USER"));

            userRepository.saveAll(Arrays.asList(admin, user));
        }
    }

    private User createUser(String username, String email, String password, List<String> roleNames) {
        User user = new User(null, username, "1", email, encoder.encode(password));

        List<Role> roles = roleRepository.findByAuthorityIn(roleNames);
        roles.forEach(user::addRole);

        return user;
    }

}
