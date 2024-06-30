package com.fiapst1.os_service.controller;

import com.fiapst1.os_service.entity.OrdemServico;
import com.fiapst1.os_service.enums.PrioridadeEnum;
import com.fiapst1.os_service.enums.StatusEnum;
import com.fiapst1.os_service.service.OrdemServicoService;
import com.fiapst1.os_service.utils.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @RequestMapping(value = "/os/healthcheck", method = RequestMethod.GET)
    public ResponseEntity healthCheck(){
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/os", method = RequestMethod.POST)
    public ResponseEntity createOrdemDeServico(@RequestBody OrdemServico ordemServico) {
        try {
            OrdemServico os = ordemServicoService.createOrdemServico(ordemServico);
            return new ResponseEntity<>(os, HttpStatus.OK);

        } catch (CustomException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/os", method = RequestMethod.PUT)
    public ResponseEntity updateOrdemDeServico(@RequestBody OrdemServico ordemServico) {
        try {
            OrdemServico os = ordemServicoService.updateOrdemServico(ordemServico);
            return new ResponseEntity<>(os, HttpStatus.OK);

        } catch (CustomException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/os", method = RequestMethod.DELETE)
    public ResponseEntity deleteOrdemDeServico(@RequestBody OrdemServico ordemServico) {
        try {
            ordemServicoService.removeOrdemServico(ordemServico);
            return new ResponseEntity<>("REMOVIDO COM SUCESSO", HttpStatus.OK);

        } catch (CustomException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/os/{id}/", method = RequestMethod.GET)
    public ResponseEntity findByOrdemServicoId(@PathVariable Long id){
        try {
            OrdemServico os = ordemServicoService.getOrdemServicoRepository().findById(id).orElse(null);

            if (os != null) {
                return new ResponseEntity<>(os, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(os, HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/os", method = RequestMethod.GET)
    public ResponseEntity<List<OrdemServico>> listarTodasOrdensDeServico() {
        try {
            List<OrdemServico> ordensDeServico = ordemServicoService.listarTodasOrdensDeServico();
            return new ResponseEntity<>(ordensDeServico, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/os/usr-criador/{usuarioCriadorId}")
    public ResponseEntity<List<OrdemServico>> buscarOrdensDeServicoPorCriador(@PathVariable Long usuarioCriadorId) {
        try {
            List<OrdemServico> ordensDeServico = ordemServicoService.buscarOrdensDeServicoPorCriador(usuarioCriadorId);
            return new ResponseEntity<>(ordensDeServico, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/os/usr-responsavel/{usuarioResponsavelId}")
    public ResponseEntity<List<OrdemServico>> buscarOrdensDeServicoPorResponsavel(@PathVariable Long usuarioResponsavelId) {
        try {
            List<OrdemServico> ordensDeServico = ordemServicoService.buscarOrdensDeServicoPorResponsavel(usuarioResponsavelId);
            return new ResponseEntity<>(ordensDeServico, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/os/usr-responsavel/{usuarioResponsavelId}/{id}")
    public ResponseEntity<?> buscarOrdemDeServicoPorResponsavelId(@PathVariable Long usuarioResponsavelId, @PathVariable Long id) {
        try {
            OrdemServico os = ordemServicoService.buscarOrdemDeServicoPorResponsavelEId(usuarioResponsavelId, id);
            return new ResponseEntity<>(os, HttpStatus.OK);
        } catch (CustomException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/os/status/{status}")
    public ResponseEntity<List<OrdemServico>> buscarOrdensDeServicoPorStatus(@PathVariable StatusEnum status) {
        try {
            List<OrdemServico> ordensDeServico = ordemServicoService.buscarOrdensDeServicoPorStatus(status);
            return new ResponseEntity<>(ordensDeServico, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/os/prioridade/{prioridade}")
    public ResponseEntity<List<OrdemServico>> buscarOrdensDeServicoPorPrioridade(@PathVariable PrioridadeEnum prioridade) {
        try {
            List<OrdemServico> ordensDeServico = ordemServicoService.buscarOrdensDeServicoPorPrioridade(prioridade);
            return new ResponseEntity<>(ordensDeServico, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/os/status/{status}/usr-responsavel/{usuarioResponsavelId}")
    public ResponseEntity<List<OrdemServico>> buscarOrdensDeServicoPorStatusEResponsavel(@PathVariable StatusEnum status, @PathVariable Long usuarioResponsavelId) {
        try {
            List<OrdemServico> ordensDeServico = ordemServicoService.buscarOrdensDeServicoPorStatusEResponsavel(status, usuarioResponsavelId);
            return new ResponseEntity<>(ordensDeServico, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/os/status/{status}/usr-criador/{usuarioCriadorId}")
    public ResponseEntity<List<OrdemServico>> buscarOrdensDeServicoPorStatusECriador(@PathVariable StatusEnum status, @PathVariable Long usuarioCriadorId) {
        try {
            List<OrdemServico> ordensDeServico = ordemServicoService.buscarOrdensDeServicoPorStatusECriador(status, usuarioCriadorId);
            return new ResponseEntity<>(ordensDeServico, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
