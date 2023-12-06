package com.dulino.desafio.enums;

import lombok.Getter;

@Getter
public enum WorkshopServiceStatus {

    SCHEDULED("Agendado"),
    IN_PROGRESS("Em Andamento"),
    FINISHED("Finalizado"),
    CANCELED("Cancelado");

    private final String description;

    WorkshopServiceStatus(String description) {
        this.description = description;
    }
}
