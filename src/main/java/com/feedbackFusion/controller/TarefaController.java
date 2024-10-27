package com.feedbackFusion.controller;

import com.feedbackFusion.dto.TarefaDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.feedbackFusion.service.TarefaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/tarefas", produces = MediaType.APPLICATION_JSON_VALUE)
public class TarefaController {
    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> create(@RequestBody TarefaDTO tarefaDTO){
        TarefaDTO createdTarefa = tarefaService.create(tarefaDTO);
        return ResponseEntity.ok(createdTarefa);
    }

    @PutMapping("/{tarefaId}")
    public ResponseEntity<TarefaDTO> update(@PathVariable("tarefaId") Long tarefaId, @RequestBody TarefaDTO tarefaDTO){
        TarefaDTO updatedFeedback = tarefaService.update(tarefaId, tarefaDTO);
        return ResponseEntity.ok(updatedFeedback);
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> getAll(){
        List<TarefaDTO> tarefas = tarefaService.getAll();
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{tarefaId}")
    public ResponseEntity<TarefaDTO> getById(@PathVariable("tarefaId") Long tarefaId) {
        TarefaDTO tarefas = tarefaService.getById(tarefaId);
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/colaborador/{colaboradorId}")
    public ResponseEntity<List<TarefaDTO>> getByColaboradorId(@PathVariable("colaboradorId") Long colaboradorId) {
        List<TarefaDTO> tarefas = tarefaService.getByColaboradorId(colaboradorId);
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/gestor/{gestorId}/colaborador/{colaboradorId}")
    public ResponseEntity<List<TarefaDTO>> getByGestorIdAndColaboradorId(@PathVariable("gestorId") Long gestorId, @PathVariable("colaboradorId") Long colaboradorId){
        List<TarefaDTO> tarefas = tarefaService.getByGestorIdAndColaboradorId(gestorId, colaboradorId);
        return ResponseEntity.ok(tarefas);
    }

    @DeleteMapping("/{tarefaId}")
    public ResponseEntity<String> delete(@PathVariable("tarefaId") Long tarefaId){
        String tarefaDeletada = tarefaService.delete(tarefaId);
        return ResponseEntity.ok(tarefaDeletada);
    }
}
