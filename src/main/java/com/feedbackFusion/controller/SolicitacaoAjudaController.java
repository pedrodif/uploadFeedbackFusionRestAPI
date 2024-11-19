package com.feedbackFusion.controller;

import com.feedbackFusion.dto.SolicitacaoAjudaDTO;
import com.feedbackFusion.service.SolicitacaoAjudaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/solicitacoesAjuda", produces = MediaType.APPLICATION_JSON_VALUE)
public class SolicitacaoAjudaController {

    @Autowired
    private SolicitacaoAjudaService solicitacaoAjudaService;

    @PostMapping
    public ResponseEntity<SolicitacaoAjudaDTO> create(@RequestBody SolicitacaoAjudaDTO solicitacaoAjudaDTO) {
        SolicitacaoAjudaDTO createdSolicitacaoAjuda = solicitacaoAjudaService.create(solicitacaoAjudaDTO);
        return ResponseEntity.ok(createdSolicitacaoAjuda);
    }

    @GetMapping
    public ResponseEntity<List<SolicitacaoAjudaDTO>> getAll() {
        List<SolicitacaoAjudaDTO> solicitacoesAjuda = solicitacaoAjudaService.getAll();
        return ResponseEntity.ok(solicitacoesAjuda);
    }

    @GetMapping("/{solicitacaoAjudaId}")
    public ResponseEntity<SolicitacaoAjudaDTO> getById(@PathVariable("solicitacaoAjudaId") Long solicitacaoAjudaId) {
        SolicitacaoAjudaDTO solicitacaoAjuda = solicitacaoAjudaService.getById(solicitacaoAjudaId);
        return ResponseEntity.ok(solicitacaoAjuda);
    }

    @GetMapping("/monitor/{monitorId}")
    public ResponseEntity<List<SolicitacaoAjudaDTO>> getByMonitorId(@PathVariable("monitorId") Long monitorId) {
        List<SolicitacaoAjudaDTO> solicitacoesAjuda = solicitacaoAjudaService.getByMonitorId(monitorId);
        return ResponseEntity.ok(solicitacoesAjuda);
    }

    @DeleteMapping("/{solicitacaoAjudaId}")
    public ResponseEntity<String> delete(@PathVariable("solicitacaoAjudaId") Long solicitacaoAjudaId) {
        String solicitacaoAjudaDeletada = solicitacaoAjudaService.delete(solicitacaoAjudaId);
        return ResponseEntity.ok(solicitacaoAjudaDeletada);
    }
}
