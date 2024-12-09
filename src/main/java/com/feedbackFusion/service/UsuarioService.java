package com.feedbackFusion.service;

import com.feedbackFusion.dto.UsuarioDTO;
import com.feedbackFusion.model.Equipe;
import com.feedbackFusion.model.Usuario;
import com.feedbackFusion.repository.EquipeRepository;
import com.feedbackFusion.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EquipeRepository equipeRepository;

    private void configurarUsuario(Usuario usuario, UsuarioDTO usuarioDTO) {
        usuario.setNome(usuarioDTO.getNome());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setCargo(usuarioDTO.getCargo());
        usuario.setEmpresa(usuarioDTO.getEmpresa());
        usuario.setDepartamento(usuarioDTO.getDepartamento());
        usuario.setStatusMonitor(usuarioDTO.isStatusMonitor());
        usuario.setPontuacaoTotal(usuarioDTO.getPontuacaoTotal());
        usuario.setAvatar(usuarioDTO.getAvatar());

        if (usuarioDTO.getEquipeId() != null) {
            Equipe equipe = equipeRepository.findById(usuarioDTO.getEquipeId())
                    .orElseThrow(() -> new EntityNotFoundException("Equipe não encontrada com ID: " + usuarioDTO.getEquipeId()));
            usuario.setEquipe(equipe);
        }
    }

    public UsuarioDTO create(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        configurarUsuario(usuario, usuarioDTO);
        repository.save(usuario);
        usuarioDTO.setId(usuario.getId());
        return usuarioDTO;
    }

    public void updatePontuacao(Long usuarioId, int pontuacao) {
        Usuario usuario = repository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + usuarioId));

        usuario.setPontuacaoTotal(usuario.getPontuacaoTotal() + pontuacao);
        repository.save(usuario);
    }

    public void updateEquipe(Long usuarioId, Long equipeId){
        Usuario usuario = repository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + usuarioId));

        if (equipeId == null) {
            usuario.setEquipe(null);
        } else {
            Equipe equipe = equipeRepository.findById(equipeId)
                    .orElseThrow(() -> new EntityNotFoundException("Equipe não encontrada com ID: " + equipeId));
            usuario.setEquipe(equipe);
        }

        repository.save(usuario);
    }

    public UsuarioDTO update(Long usuarioId, UsuarioDTO usuarioDTO) {
        Usuario usuario = repository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + usuarioId));

        configurarUsuario(usuario, usuarioDTO);
        repository.save(usuario);
        usuarioDTO.setId(usuario.getId());
        return usuarioDTO;
    }

    private UsuarioDTO converterUsurio (Usuario usuario) {
        UsuarioDTO usuarioConvertido = new UsuarioDTO();
        usuarioConvertido.setId(usuario.getId());
        usuarioConvertido.setEmail(usuario.getEmail());
        usuarioConvertido.setSenha(usuario.getSenha());
        usuarioConvertido.setStatusMonitor(usuario.isStatusMonitor());
        usuarioConvertido.setNome(usuario.getNome());
        usuarioConvertido.setEmpresa(usuario.getEmpresa());
        usuarioConvertido.setDepartamento(usuario.getDepartamento());
        usuarioConvertido.setCargo(usuario.getCargo());
        usuarioConvertido.setPontuacaoTotal(usuario.getPontuacaoTotal());
        usuarioConvertido.setAvatar(usuario.getAvatar());

        if (usuario.getEquipe() != null) {
            usuarioConvertido.setEquipeId(usuario.getEquipe().getId());
        }

        return usuarioConvertido;
    }

    public List<UsuarioDTO> getAll() {
        return repository.findAll().stream().map(this::converterUsurio).collect(Collectors.toList());
    }

    public UsuarioDTO getById(Long usuarioId){
        Usuario usuarioRecuperado = repository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + usuarioId));

        return converterUsurio(usuarioRecuperado);
    }

    public String delete(Long usuarioId){
        repository.deleteById(usuarioId);
        return "Usuário deletado com sucesso!";
    }
}
