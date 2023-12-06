package com.dulino.desafio.dtos.request;

import com.dulino.desafio.enums.WorkshopServiceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkShopTaskInsertDTO {
    @NotNull(message = "Campo requerido")
    private WorkshopServiceType serviceType;
    @NotBlank(message = "Campo requerido")
        private String vehicleId;
}
