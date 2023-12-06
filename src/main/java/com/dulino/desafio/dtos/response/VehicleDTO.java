package com.dulino.desafio.dtos.response;

import com.dulino.desafio.entity.Vehicle;
import com.dulino.desafio.entity.WorkshopTask;
import com.dulino.desafio.entity.embedded.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VehicleDTO {
    private String id;
    private String plate;
    private String model;
    private Integer year;

    private Owner owner;

    private List<WorkshopTaskDTO> tasks = new ArrayList<>();

    public VehicleDTO(Vehicle entity) {
        this.id = entity.getId();
        this.plate = entity.getPlate();
        this.model = entity.getModel();
        this.year = entity.getYear();
        this.owner = new Owner(entity.getOwner().getId(), entity.getOwner().getFullName());
    }

    public VehicleDTO(Vehicle entity, List<WorkshopTask> tasks) {
        this.id = entity.getId();
        this.plate = entity.getPlate();
        this.model = entity.getModel();
        this.year = entity.getYear();
        this.owner = new Owner(entity.getOwner().getId(), entity.getOwner().getFullName());
        for (WorkshopTask t : tasks) {
            this.tasks.add(new WorkshopTaskDTO(t));
        }
    }
}
