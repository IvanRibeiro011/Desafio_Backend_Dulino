package com.dulino.desafio.dtos.response;

import com.dulino.desafio.entity.Vehicle;
import com.dulino.desafio.entity.WorkshopTask;
import com.dulino.desafio.entity.embedded.Owner;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VehicleTaskDTO {
    private String id;
    private String plate;
    private String model;
    private Owner owner;

    public VehicleTaskDTO(Vehicle entity) {
        this.id = entity.getId();
        this.plate = entity.getPlate();
        this.model = entity.getModel();
        this.owner = entity.getOwner();
    }
}
