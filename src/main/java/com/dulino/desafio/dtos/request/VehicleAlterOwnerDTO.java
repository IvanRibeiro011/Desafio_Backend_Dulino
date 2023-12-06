package com.dulino.desafio.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VehicleAlterOwnerDTO {

    @NotBlank(message = "Campo requerido")
    private String vehicleId;
    @NotBlank(message = "Campo requerido")
    private String ownerId;
}
