package com.dulino.desafio.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VehicleInsertDTO {
    @NotBlank(message = "Campo requerido")
    @Size(min = 7, max = 7, message = "O campo precisa ter 7 caracteres")
    private String plate;
    @NotBlank(message = "Campo requerido")
    private String model;
    @Positive
    private Integer year;
    @NotBlank(message = "Campo requerido")
    private String ownerId;
}
