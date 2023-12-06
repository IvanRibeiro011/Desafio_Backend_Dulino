package com.dulino.desafio.controller;

import com.dulino.desafio.dtos.request.UserInsertDTO;
import com.dulino.desafio.dtos.response.UserDTO;
import com.dulino.desafio.dtos.request.UserUpdateDTO;
import com.dulino.desafio.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Usuários", description = "Operações relacionadas a usuários")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    @Operation(summary = "Busca todos os usuários")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    @Operation(summary = "Busca usuários por Id")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Insere usuário no banco de dados")
    @PostMapping
    public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserInsertDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(dto));
    }

    @Operation(summary = "Atualiza usuário passando o Id e os campos a serem atualizados")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id") String id,@Valid @RequestBody UserUpdateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
    @Operation(summary = "Deleta um usuário")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
