package com.dulino.desafio.enums;

import lombok.Getter;

@Getter
public enum WorkshopServiceType {
    OIL_CHANGE("Troca de óleo"),
    ALIGNMENT_AND_BALANCING("Alinhamento e Balanceamento"),
    GENERAL_MAINTENANCE("Manutenção Geral"),
    OVERHAUL_OF_BRAKE_COMPONENTS("Revisão de componentes de freio");

    private final String description;

    WorkshopServiceType(String description) {
        this.description = description;
    }

}
