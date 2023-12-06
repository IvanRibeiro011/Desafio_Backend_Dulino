package com.dulino.desafio.dtos.response;

import com.dulino.desafio.dtos.response.VehicleTaskDTO;
import com.dulino.desafio.entity.WorkshopTask;
import com.dulino.desafio.enums.WorkshopServiceStatus;
import com.dulino.desafio.enums.WorkshopServiceType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkshopTaskDTO {
    private String id;
    private WorkshopServiceType serviceType;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private WorkshopServiceStatus serviceStatus;
    private VehicleTaskDTO vehicle;

    public WorkshopTaskDTO(WorkshopTask entity) {
        this.id = entity.getId();
        this.serviceType = entity.getServiceType();
        this.startedAt = entity.getStartedAt();
        this.finishedAt = entity.getFinishedAt();
        this.serviceStatus = entity.getServiceStatus();
        this.vehicle = new VehicleTaskDTO(entity.getVehicle());
    }
}
