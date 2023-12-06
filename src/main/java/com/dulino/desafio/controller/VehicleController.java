package com.dulino.desafio.controller;

import com.dulino.desafio.dtos.request.VehicleAlterOwnerDTO;
import com.dulino.desafio.dtos.request.VehicleInsertDTO;
import com.dulino.desafio.dtos.request.VehicleUpdateDTO;
import com.dulino.desafio.dtos.response.VehicleDTO;
import com.dulino.desafio.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(name = "Veículos", description = "Operações relacionadas a veículos")
@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }
    @Operation(summary = "Busca Todos os veículos")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<VehicleDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    @Operation(summary = "Busca veículos por Id")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.findById(id));
    }
    @Operation(summary = "Busca veículos por Id de um usuário")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/owner")
    public ResponseEntity<List<VehicleDTO>> findByOwnerId(@RequestParam("id") String id) {
        return ResponseEntity.ok(service.findByOwnerId(id));
    }
    @Operation(summary = "Insere veículos no banco de dados")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PostMapping
    public ResponseEntity<VehicleDTO> insert(@Valid @RequestBody VehicleInsertDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(dto));
    }
    @Operation(summary = "Atualiza veículo passando o Id e os campos a serem atualizados")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PutMapping("{id}")
    public ResponseEntity<VehicleDTO> update(@PathVariable("id") String id, @Valid @RequestBody VehicleUpdateDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }
    @Operation(summary = "Atualiza o proprietário do veículo")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @PatchMapping
    public ResponseEntity<VehicleDTO> alterOwner(@Valid @RequestBody VehicleAlterOwnerDTO dto) {
        return ResponseEntity.ok(service.alterOwner(dto));
    }
    @Operation(summary = "Deleta um veículo")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<VehicleDTO> delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
