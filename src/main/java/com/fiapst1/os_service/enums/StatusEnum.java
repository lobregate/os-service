package com.fiapst1.os_service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEnum {
    PENDENTE("OS pendente", "Ordem de serviço pendente de execução"),
    ANDAMENTO("OS em andamento", "Ordem de serviço em andamento"),
    FINALIZADA("OS finalizada", "Ordem de serviço finalizada"),
    CANCELADA("OS Cancelada", "Ordem de serviço cancelada (não houve atuação)");

    private String descricao;

    private String descricaoCompleta;
}
