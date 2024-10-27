package com.feedbackFusion.controller;

import com.feedbackFusion.dto.UsuarioDTO;
import com.feedbackFusion.service.UsuarioService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO createdUsuario = usuarioService.create(usuarioDTO);
        return ResponseEntity.ok(createdUsuario);
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable("usuarioId") Long usuarioId, @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO updatedUsuario = usuarioService.update(usuarioId, usuarioDTO);
        return ResponseEntity.ok(updatedUsuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAll() {
        List<UsuarioDTO> usuarios = usuarioService.getAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioDTO> getById(@PathVariable("usuarioId") Long usuarioId) {
        UsuarioDTO usuario = usuarioService.getById(usuarioId);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<String> delete(@PathVariable("usuarioId") Long usuarioId) {
        String usuarioDeletado = usuarioService.delete(usuarioId);
        return ResponseEntity.ok(usuarioDeletado);
    }
}
