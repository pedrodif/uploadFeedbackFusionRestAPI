package com.feedbackFusion.service;

import com.feedbackFusion.dto.AuthDTO;
import com.feedbackFusion.model.Usuario;
import com.feedbackFusion.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AuthService {
    @Autowired
    private UsuarioRepository repository;

    public AuthDTO autenticar(AuthDTO authDTO) {
        Usuario usuario = repository.findByEmail(authDTO.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o e-mail: " + authDTO.getEmail()));

        if (!usuario.getSenha().equals(authDTO.getSenha())) {
            throw new IllegalArgumentException("Senha incorreta.");
        }

        authDTO.setId(usuario.getId());
        return authDTO;
    }
}
