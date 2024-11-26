package com.feedbackFusion.controller;

import com.feedbackFusion.dto.ColaboradorIndicadoresDTO;
import com.feedbackFusion.dto.EquipeIndicadoresDTO;
import com.feedbackFusion.service.IndicadoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/indicadores", produces = MediaType.APPLICATION_JSON_VALUE)
public class IndicadoresController {
    @Autowired
    private IndicadoresService indicadoresService;

    @GetMapping("/colaborador/{colaboradorId}")
    public ResponseEntity<ColaboradorIndicadoresDTO> getByColaboradorId(@PathVariable("colaboradorId") Long colaboradorId) {
        ColaboradorIndicadoresDTO indicadores = indicadoresService.getByColaboradorId(colaboradorId);
        return ResponseEntity.ok(indicadores);
    }

    @GetMapping("/equipe/{equipeId}")
    public ResponseEntity<EquipeIndicadoresDTO> getByEquipeId(@PathVariable("equipeId") Long equipeId) {
        EquipeIndicadoresDTO indicadores = indicadoresService.getByEquipeId(equipeId);
        return ResponseEntity.ok(indicadores);
    }
}
