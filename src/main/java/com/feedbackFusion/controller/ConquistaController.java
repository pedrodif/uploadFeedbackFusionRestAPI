package com.feedbackFusion.controller;

import com.feedbackFusion.dto.ConquistaDTO;
import com.feedbackFusion.service.ConquistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/conquistas", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConquistaController {
    @Autowired
    private ConquistaService conquistaService;

    @PostMapping
    public ResponseEntity<ConquistaDTO> create(@RequestBody ConquistaDTO conquistaDTO){
        ConquistaDTO createdConquista = conquistaService.create(conquistaDTO);
        return ResponseEntity.ok(createdConquista);
    }

    @GetMapping
    public ResponseEntity<List<ConquistaDTO>> getAll(){
        List<ConquistaDTO> conquistas = conquistaService.getAll();
        return ResponseEntity.ok(conquistas);
    }

    @GetMapping("/colaborador/{colaboradorId}")
    public ResponseEntity<List<ConquistaDTO>> getByColaboradorId(@PathVariable("colaboradorId") Long colaboradorId) {
        List<ConquistaDTO> conquistas = conquistaService.getByColaboradorId(colaboradorId);
        return ResponseEntity.ok(conquistas);
    }

    @GetMapping("/gestor/{gestorId}/colaborador/{colaboradorId}")
    public ResponseEntity<List<ConquistaDTO>> getByGestorIdAndColaboradorId(@PathVariable("gestorId") Long gestorId, @PathVariable("colaboradorId") Long colaboradorId){
        List<ConquistaDTO> conquistas = conquistaService.getByGestorIdAndColaboradorId(gestorId, colaboradorId);
        return ResponseEntity.ok(conquistas);
    }

    @DeleteMapping("/{conquistaId}")
    public ResponseEntity<String> delete(@PathVariable("conquistaId") Long conquistaId){
        String conquistaDeletada = conquistaService.delete(conquistaId);
        return ResponseEntity.ok(conquistaDeletada);
    }
}
