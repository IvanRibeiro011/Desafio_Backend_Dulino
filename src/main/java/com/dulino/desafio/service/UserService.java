package com.dulino.desafio.service;

import com.dulino.desafio.dtos.request.UserInsertDTO;
import com.dulino.desafio.dtos.request.UserUpdateDTO;
import com.dulino.desafio.dtos.response.UserDTO;
import com.dulino.desafio.entity.Role;
import com.dulino.desafio.entity.User;
import com.dulino.desafio.exceptions.NonExistentRoleException;
import com.dulino.desafio.exceptions.UserAlreadyExistsException;
import com.dulino.desafio.exceptions.UserNotFoundException;
import com.dulino.desafio.repository.RoleRepository;
import com.dulino.desafio.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository repository, PasswordEncoder encoder, RoleRepository roleRepository) {
        this.repository = repository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    public UserDTO findById(String id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));
        return new UserDTO(user, user.getVehicles());
    }

    public List<UserDTO> findAll() {
        return repository.findAll().stream().map(user -> new UserDTO(user, user.getVehicles())).toList();
    }

    public UserDTO insert(UserInsertDTO dto) {
        if (!repository.existsByEmail(dto.getEmail())) {
            User user = new User();
            copyDtoToEntity(dto, user);
            user.setPassword(encoder.encode(dto.getPassword()));
            for (String s : dto.getRoleName()) {
                Role role = roleRepository.findByAuthority(s).orElseThrow(() -> new NonExistentRoleException("Permissão não encontrada"));
                user.addRole(role);
            }
            user = repository.save(user);
            return new UserDTO(user, user.getVehicles());
        } else {
            throw new UserAlreadyExistsException("Usuário já cadastrado");
        }
    }


    public UserDTO update(String id, UserUpdateDTO dto) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário Não encontrado"));
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        for (String s : dto.getRoleName()) {
            Role role = roleRepository.findByAuthority(s).orElseThrow(() -> new NonExistentRoleException("Permissão não encontrada"));
            user.addRole(role);
        }
        user = repository.save(user);
        return new UserDTO(user);
    }

    public void delete(String id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        repository.delete(user);
    }

    private static void copyDtoToEntity(UserInsertDTO dto, User user) {
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
