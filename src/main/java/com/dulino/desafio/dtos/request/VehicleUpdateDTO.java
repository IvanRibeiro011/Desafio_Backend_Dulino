package com.dulino.desafio.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VehicleUpdateDTO {
    @NotBlank(message = "Campo requerido")
    private String plate;
    @NotBlank(message = "Campo requerido")
    private String model;
    @Positive
    private Integer year;

}
