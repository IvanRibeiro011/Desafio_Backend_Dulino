package com.dulino.desafio.controller;

import com.dulino.desafio.dtos.request.WorkShopTaskInsertDTO;
import com.dulino.desafio.dtos.request.WorkshopTaskUpdateDTO;
import com.dulino.desafio.dtos.response.WorkshopTaskDTO;
import com.dulino.desafio.service.WorkshopTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tarefas de Oficina", description = "Operações relacionadas as tarefas de oficina")
@RestController
@RequestMapping("/tasks")
public class WorkShopTaskController {

    private final WorkshopTaskService service;

    public WorkShopTaskController(WorkshopTaskService service) {
        this.service = service;
    }

    @Operation(summary = "Busca todas as Tarefas")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<WorkshopTaskDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Busca tarefas por id")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("{id}")
    public ResponseEntity<WorkshopTaskDTO> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Insere tarefas no banco de dados")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    public ResponseEntity<WorkshopTaskDTO> insert(@Valid @RequestBody WorkShopTaskInsertDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(dto));
    }

    @Operation(summary = "Atualiza tarefas")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("{id}")
    public ResponseEntity<WorkshopTaskDTO> update(@PathVariable("id") String id, @Valid @RequestBody WorkshopTaskUpdateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Deleta tarefas")
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
