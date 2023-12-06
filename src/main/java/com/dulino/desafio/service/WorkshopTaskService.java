package com.dulino.desafio.service;

import com.dulino.desafio.dtos.request.WorkShopTaskInsertDTO;
import com.dulino.desafio.dtos.request.WorkshopTaskUpdateDTO;
import com.dulino.desafio.dtos.response.WorkshopTaskDTO;
import com.dulino.desafio.entity.Vehicle;
import com.dulino.desafio.entity.WorkshopTask;
import com.dulino.desafio.enums.WorkshopServiceStatus;
import com.dulino.desafio.exceptions.TaskNotFoundException;
import com.dulino.desafio.exceptions.VehicleNotFoundException;
import com.dulino.desafio.repository.VehicleRepository;
import com.dulino.desafio.repository.WorkshopTaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkshopTaskService {

    private final WorkshopTaskRepository repository;

    private final VehicleRepository vehicleRepository;

    public WorkshopTaskService(WorkshopTaskRepository repository, VehicleRepository vehicleRepository) {
        this.repository = repository;
        this.vehicleRepository = vehicleRepository;
    }

    public List<WorkshopTaskDTO> findAll() {
        return repository.findAll().stream().map(WorkshopTaskDTO::new).toList();
    }

    public WorkshopTaskDTO findById(String id) {
        WorkshopTask task = repository.findById(id).orElseThrow(() -> new TaskNotFoundException("Serviço não encontrado"));
        return new WorkshopTaskDTO(task);
    }

    public WorkshopTaskDTO insert(WorkShopTaskInsertDTO dto) {
        WorkshopTask task = new WorkshopTask();
        copyDtoToEntity(dto, task);
        Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId()).orElseThrow(() -> new VehicleNotFoundException("Veiculo não encontrado"));
        task.setVehicle(vehicle);
        task = repository.save(task);
        vehicle.getTasks().add(task);
        vehicleRepository.save(vehicle);
        return new WorkshopTaskDTO(task);
    }

    public WorkshopTaskDTO update(String id, WorkshopTaskUpdateDTO dto) {
        WorkshopTask task = repository.findById(id).orElseThrow(() -> new TaskNotFoundException("Serviço não encontrado"));
        if (dto.getServiceType() != null) {
            task.setServiceType(dto.getServiceType());
        }

        if (dto.getStartedAt() != null) {
            task.setStartedAt(dto.getStartedAt());
            task.setServiceStatus(WorkshopServiceStatus.IN_PROGRESS);
        }

        if (dto.getFinishedAt() != null) {
            task.setFinishedAt(dto.getFinishedAt());
            task.setServiceStatus(WorkshopServiceStatus.FINISHED);
        }

        if(dto.getServiceStatus() != null){
            task.setServiceStatus(dto.getServiceStatus());
        }

        task = repository.save(task);

        return new WorkshopTaskDTO(task);
    }

    public void delete(String id) {
        WorkshopTask task = repository.findById(id).orElseThrow(() -> new TaskNotFoundException("Serviço não encontrado"));
        Vehicle vehicle = task.getVehicle();
        vehicle.getTasks().remove(task);
        vehicleRepository.save(vehicle);
        repository.delete(task);
    }

    private static void copyDtoToEntity(WorkShopTaskInsertDTO dto, WorkshopTask task) {
        task.setServiceType(dto.getServiceType());
        task.setRegisteredAt(LocalDateTime.now());
        task.setServiceStatus(WorkshopServiceStatus.SCHEDULED);
    }

}
