package com.fiapst1.os_service.entity;

import com.fiapst1.os_service.enums.PrioridadeEnum;
import com.fiapst1.os_service.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ORDEM_SERVICO")
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "DATA_CRIACAO")
    private LocalDateTime dataCriacao;

    @Column(name = "DATA_ALTERACAO")
    private LocalDateTime dataAlteracao;

    @Column(name = "DATA_CONCLUSAO")
    private LocalDateTime dataConclusao;

    @Column(name = "PRIORIDADE")
    @Enumerated(EnumType.STRING)
    private PrioridadeEnum prioridade;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;


    @Column(name = "ID_ATIVO")
    private Long ativoId;

    @Column(name = "ID_USU_CRIADOR")
    private Long usuarioCriadorId;

    @Column(name = "ID_USU_RESPONSAVEL")
    private Long usuarioResponsavelId;

}
