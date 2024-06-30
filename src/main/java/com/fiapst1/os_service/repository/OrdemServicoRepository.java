package com.fiapst1.os_service.repository;

import com.fiapst1.os_service.entity.OrdemServico;
import com.fiapst1.os_service.enums.PrioridadeEnum;
import com.fiapst1.os_service.enums.StatusEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrdemServicoRepository extends CrudRepository<OrdemServico, Long> {
    List<OrdemServico> findByUsuarioCriadorId(Long usuarioCriadorId);
    List<OrdemServico> findByUsuarioResponsavelId(Long usuarioResponsavelId);
    Optional<OrdemServico> findByUsuarioResponsavelIdAndId(Long usuarioResponsavelId, Long id);
    List<OrdemServico> findByStatus(StatusEnum status);
    List<OrdemServico> findByPrioridade(PrioridadeEnum prioridade);
    List<OrdemServico> findByStatusAndUsuarioResponsavelId(StatusEnum status, Long usuarioResponsavelId);
    List<OrdemServico> findByStatusAndUsuarioCriadorId(StatusEnum status, Long usuarioCriadorId);
}
