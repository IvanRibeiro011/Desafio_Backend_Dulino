package com.dulino.desafio.dtos.request;

import com.dulino.desafio.enums.WorkshopServiceStatus;
import com.dulino.desafio.enums.WorkshopServiceType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WorkshopTaskUpdateDTO {
    @NotNull(message = "Campo Requerido")
    private WorkshopServiceType serviceType;
    private LocalDateTime registeredAt;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    @NotNull(message = "Campo Requerido")
    private WorkshopServiceStatus serviceStatus;
}
