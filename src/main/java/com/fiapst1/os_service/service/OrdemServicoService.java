package com.fiapst1.os_service.service;

import com.fiapst1.os_service.entity.OrdemServico;
import com.fiapst1.os_service.enums.PrioridadeEnum;
import com.fiapst1.os_service.enums.StatusEnum;
import com.fiapst1.os_service.repository.OrdemServicoRepository;
import com.fiapst1.os_service.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdemServicoService {
    @Autowired
    OrdemServicoRepository ordemServicoRepository;

    public OrdemServicoRepository getOrdemServicoRepository() {//remover depois
        return ordemServicoRepository;
    }

    //método para criar nova OS
    public OrdemServico createOrdemServico(OrdemServico ordemServico) throws CustomException {
        if (ordemServico != null) {
            ordemServico.setDataCriacao(LocalDateTime.now());

            return this.saveOrdemServico(ordemServico);

        } else {
            throw new CustomException("Erro ao persistir ordem de serviço: OS não preenchida", new Throwable());
        }

    }

    //Método para editar OS
    public OrdemServico updateOrdemServico(OrdemServico ordemServico) throws CustomException {
        if (ordemServico != null) {
            return this.saveOrdemServico(ordemServico);

        } else {
            throw new CustomException("Erro ao persistir ordem de serviço: OS não preenchida", new Throwable());
        }

    }

    private OrdemServico saveOrdemServico (OrdemServico ordemServico) throws CustomException {
        ordemServico.setDataAlteracao(LocalDateTime.now());

        try {
            return ordemServicoRepository.save(ordemServico);
        } catch (DataIntegrityViolationException e) {
            // Tratamento para violação de integridade de dados, como chave duplicada, etc.
            throw new CustomException("Erro de integridade de dados: " + e.getMessage(), e);
        } catch (Exception e) {
            // Tratamento genérico para outras exceções
            throw new CustomException("Erro ao persistir ordem de serviço: " + e.getMessage(), e);
        }

    }


    //Método que finaliza a OS
    public OrdemServico finalizaOrdemServico(OrdemServico ordemServico) throws CustomException {
        if (ordemServico != null) {
            ordemServico.setDataConclusao(LocalDateTime.now());
            ordemServico.setStatus(StatusEnum.FINALIZADA);

            return this.saveOrdemServico(ordemServico);

        } else {
            throw new CustomException("Erro ao persistir ordem de serviço: OS não preenchida", new Throwable());
        }

    }

    //Método que remove a OS
    public void removeOrdemServico(OrdemServico ordemServico) throws CustomException {
        if (ordemServico != null) {
            try {
                ordemServicoRepository.delete(ordemServico);
            } catch (Exception e) {
                throw new CustomException("Problema ao remover ordem de serviço: " + e.getMessage(), e);
            }

        } else {
            throw new CustomException("Erro ao remover ordem de serviço: OS não preenchida", new Throwable());
        }

    }

    // Método que lista todas as OSs (List)
    public List<OrdemServico> listarTodasOrdensDeServico() {
        return (List<OrdemServico>) ordemServicoRepository.findAll();
    }

    // Método que busca OS por ID
    public OrdemServico buscarOrdemDeServicoPorId(Long id) throws CustomException {
        return ordemServicoRepository.findById(id)
                .orElseThrow(() -> new CustomException("Ordem de serviço não encontrada", new Throwable()));
    }

    // Método que busca OSs por id de criador (List)
    public List<OrdemServico> buscarOrdensDeServicoPorCriador(Long usuarioCriadorId) {
        return ordemServicoRepository.findByUsuarioCriadorId(usuarioCriadorId);
    }

    // Método que busca OSs por id de responsável (quem pegou a OS para resolver) (List)
    public List<OrdemServico> buscarOrdensDeServicoPorResponsavel(Long usuarioResponsavelId) {
        return ordemServicoRepository.findByUsuarioResponsavelId(usuarioResponsavelId);
    }

    // Método que busca a OS por id do responsável e pelo id da OS
    public OrdemServico buscarOrdemDeServicoPorResponsavelEId(Long usuarioResponsavelId, Long id) throws CustomException {
        return ordemServicoRepository.findByUsuarioResponsavelIdAndId(usuarioResponsavelId, id)
                .orElseThrow(() -> new CustomException("Ordem de serviço não encontrada", new Throwable()));
    }

    // Método que busca as OSs por status
    public List<OrdemServico> buscarOrdensDeServicoPorStatus(StatusEnum status) {
        return ordemServicoRepository.findByStatus(status);
    }

    // Método que busca as OSs por prioridade
    public List<OrdemServico> buscarOrdensDeServicoPorPrioridade(PrioridadeEnum prioridade) {
        return ordemServicoRepository.findByPrioridade(prioridade);
    }

    // Método que busca as OSs por status e por id do responsável
    public List<OrdemServico> buscarOrdensDeServicoPorStatusEResponsavel(StatusEnum status, Long usuarioResponsavelId) {
        return ordemServicoRepository.findByStatusAndUsuarioResponsavelId(status, usuarioResponsavelId);
    }

    // Método que busca as OSs por status e por id do criador
    public List<OrdemServico> buscarOrdensDeServicoPorStatusECriador(StatusEnum status, Long usuarioCriadorId) {
        return ordemServicoRepository.findByStatusAndUsuarioCriadorId(status, usuarioCriadorId);
    }
}
