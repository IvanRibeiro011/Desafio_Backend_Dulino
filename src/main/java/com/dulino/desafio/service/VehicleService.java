package com.dulino.desafio.service;

import com.dulino.desafio.dtos.request.VehicleAlterOwnerDTO;
import com.dulino.desafio.dtos.request.VehicleInsertDTO;
import com.dulino.desafio.dtos.request.VehicleUpdateDTO;
import com.dulino.desafio.dtos.response.VehicleDTO;
import com.dulino.desafio.entity.User;
import com.dulino.desafio.entity.Vehicle;
import com.dulino.desafio.entity.embedded.Owner;
import com.dulino.desafio.exceptions.UserNotFoundException;
import com.dulino.desafio.exceptions.VehicleNotFoundException;
import com.dulino.desafio.exceptions.VehiclePlateAlreadyRegisteredException;
import com.dulino.desafio.repository.UserRepository;
import com.dulino.desafio.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository repository;
    private final UserRepository userRepository;

    public VehicleService(VehicleRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<VehicleDTO> findAll() {
        return repository.findAll().stream().map(v -> new VehicleDTO(v, v.getTasks())).toList();
    }

    public VehicleDTO findById(String id) {
        Vehicle vehicle = repository.findById(id).orElseThrow(() -> new VehicleNotFoundException("Veículo não encontrado"));
        return new VehicleDTO(vehicle, vehicle.getTasks());
    }

    public List<VehicleDTO> findByOwnerId(String ownerId) {
        return repository.findByOwnerId(ownerId).stream().map(VehicleDTO::new).toList();
    }

    public VehicleDTO insert(VehicleInsertDTO dto) {
        if (!repository.existsByPlate(dto.getPlate()) || !repository.existsByPlateAndAndOwner_Id(dto.getPlate(), dto.getOwnerId())) {
            Vehicle vehicle = new Vehicle();
            copyDtoToEntity(dto, vehicle);
            User user = userRepository.findById(dto.getOwnerId()).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
            vehicle.setOwner(new Owner(user));
            vehicle = repository.save(vehicle);
            user.getVehicles().add(vehicle);
            userRepository.save(user);
            return new VehicleDTO(vehicle, vehicle.getTasks());
        } else {
            throw new VehiclePlateAlreadyRegisteredException("Veículo já cadastrado");
        }
    }

    public VehicleDTO update(String id, VehicleUpdateDTO dto) {
        Vehicle vehicle = repository.findById(id).orElseThrow(() -> new VehicleNotFoundException("Veículo não encontrado"));
        verifyPlate(dto);
        copyUpdateDtoToEntity(dto, vehicle);
        vehicle = repository.save(vehicle);
        return new VehicleDTO(vehicle);
    }

    public VehicleDTO alterOwner(VehicleAlterOwnerDTO dto) {
        Vehicle vehicle = repository.findById(dto.getVehicleId()).orElseThrow(() -> new VehicleNotFoundException("Veículo não encontrado"));
        User user = userRepository.findById(dto.getOwnerId()).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        vehicle.setOwner(new Owner(user));
        vehicle = repository.save(vehicle);
        user.getVehicles().remove(vehicle);
        userRepository.save(user);
        return new VehicleDTO(vehicle);
    }

    public void delete(String id) {
        Vehicle vehicle = repository.findById(id).orElseThrow(() -> new VehicleNotFoundException("Veículo não encontrado"));
        User user = userRepository.findById(vehicle.getOwner().getId()).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
        user.getVehicles().remove(vehicle);
        userRepository.save(user);
        repository.delete(vehicle);
    }

    private static void copyUpdateDtoToEntity(VehicleUpdateDTO dto, Vehicle vehicle) {
        vehicle.setPlate(dto.getPlate());
        vehicle.setYear(dto.getYear());
        vehicle.setModel(dto.getModel());
    }

    private void verifyPlate(VehicleUpdateDTO dto) {
        if (repository.existsByPlate(dto.getPlate())) {
            throw new VehiclePlateAlreadyRegisteredException("Veículo já cadastrado");
        }
    }

    private static void copyDtoToEntity(VehicleInsertDTO dto, Vehicle vehicle) {
        vehicle.setPlate(dto.getPlate());
        vehicle.setYear(dto.getYear());
        vehicle.setModel(dto.getModel());
    }
}
