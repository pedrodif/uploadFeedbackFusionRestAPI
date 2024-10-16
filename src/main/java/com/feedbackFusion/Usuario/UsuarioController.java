package com.feedbackFusion.Usuario;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseBody
    public UsuarioDTO create(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.create(usuarioDTO);
    }

    @PutMapping("/{usuarioId}")
    @ResponseBody
    public UsuarioDTO update(@PathVariable("usuarioId") Long usuarioId, @RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.update(usuarioId, usuarioDTO);
    }

    @GetMapping
    @ResponseBody
    public List<UsuarioDTO> getAll(){
        return usuarioService.getAll();
    }

   @GetMapping("/{usuarioId}")
     @ResponseBody
     public UsuarioDTO getById(@PathVariable("usuarioId") Long usuarioId){
     return usuarioService.getById(usuarioId);
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseBody
    public String delete(@PathVariable("usuarioId") Long usuarioId){
        return usuarioService.delete(usuarioId);
    }
}
