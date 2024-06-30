package com.fiapst1.os_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PrioridadeEnum {
    BAIXA("Prioridade baixa", "Prioridade baixa (pode aguardar até 48h)"),
    MEDIA("Prioridade média", "Prioridade média (pode aguardar até 12h)"),
    ALTA("Prioridade alta", "Prioridade média (necessário atuação imediata)");

    private String descricao;

    private String descricaoCompleta;

}
