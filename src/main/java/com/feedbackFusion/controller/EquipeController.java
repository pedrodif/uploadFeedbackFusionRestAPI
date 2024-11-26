package com.feedbackFusion.controller;

import com.feedbackFusion.dto.EquipeDTO;
import com.feedbackFusion.service.EquipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/equipes", produces = MediaType.APPLICATION_JSON_VALUE)
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    @PostMapping
    public ResponseEntity<EquipeDTO> create(@RequestBody EquipeDTO equipeDTO) {
        EquipeDTO createdEquipe = equipeService.create(equipeDTO);
        return ResponseEntity.ok(createdEquipe);
    }

    @PutMapping("/{equipeId}")
    public ResponseEntity<EquipeDTO> update(@PathVariable("equipeId") Long equipeId, @RequestBody EquipeDTO equipeDTO) {
        EquipeDTO updatedEquipe = equipeService.update(equipeId, equipeDTO);
        return ResponseEntity.ok(updatedEquipe);
    }

    @GetMapping("/{equipeId}")
    public ResponseEntity<EquipeDTO> getById(@PathVariable("equipeId") Long equipeId) {
        EquipeDTO equipe = equipeService.getById(equipeId);
        return ResponseEntity.ok(equipe);
    }

    @GetMapping("/gestor/{gestorId}")
    public ResponseEntity<List<EquipeDTO>> getByGestorId(@PathVariable("gestorId") Long gestorId) {
        List<EquipeDTO> equipes = equipeService.getByGestorId(gestorId);
        return ResponseEntity.ok(equipes);
    }

    @GetMapping("/colaborador/{colaboradorId}")
    public ResponseEntity<EquipeDTO> getByColaboradorId(@PathVariable("colaboradorId") Long colaboradorId) {
        EquipeDTO equipe = equipeService.getByColaboradorId(colaboradorId);
        return ResponseEntity.ok(equipe);
    }

    @DeleteMapping("/{equipeId}")
    public ResponseEntity<String> delete(@PathVariable("equipeId") Long equipeId) {
        String equipeDeletada = equipeService.delete(equipeId);
        return ResponseEntity.ok(equipeDeletada);
    }
}