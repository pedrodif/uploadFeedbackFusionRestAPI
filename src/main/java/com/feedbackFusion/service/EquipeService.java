package com.feedbackFusion.service;

import com.feedbackFusion.dto.EquipeDTO;
import com.feedbackFusion.model.Equipe;
import com.feedbackFusion.model.Usuario;
import com.feedbackFusion.repository.EquipeRepository;
import com.feedbackFusion.repository.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EquipeService {
    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    private void configurarEquipe(Equipe equipe, EquipeDTO equipeDTO) {
        equipe.setNome(equipeDTO.getNome());
        equipe.setDataEdicao(equipeDTO.getDataEdicao());

        Usuario gestor = usuarioRepository.findById(equipeDTO.getGestorId())
                .orElseThrow(() -> new EntityNotFoundException("Gestor não encontrado com ID: " + equipeDTO.getGestorId()));
        equipe.setGestor(gestor);
    }

    public EquipeDTO create(EquipeDTO equipeDTO) {
        Equipe equipe = new Equipe();

        configurarEquipe(equipe, equipeDTO);
        equipe.setDataCriacao(equipeDTO.getDataCriacao());

        for (Long colaboradorId : equipeDTO.getColaboradoresIds()) {
            Usuario colaborador = usuarioRepository.findById(colaboradorId)
                    .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado com ID: " + colaboradorId));

            if (colaborador.getEquipe() != null) {
                throw new IllegalArgumentException("O colaborador: " + colaborador.getNome() + ",já faz parte da equipe: " + colaborador.getEquipe().getNome());
            }

            equipe.addColaborador(colaborador);
        }

        equipeRepository.save(equipe);

        for (Usuario colaborador : equipe.getColaboradores()) {
            usuarioService.updateEquipe(colaborador.getId(), equipe.getId());
        }

        equipeDTO.setId(equipe.getId());
        equipeDTO.setGestor(equipe.getGestor());
        equipeDTO.setColaboradores(equipe.getColaboradores());
        return equipeDTO;
    }

    public EquipeDTO update(Long equipeId, EquipeDTO equipeDTO) {
        Equipe equipe = equipeRepository.findById(equipeId)
                .orElseThrow(() -> new EntityNotFoundException("Nenhuma equipe encontrada com o ID: " + equipeId));

        Hibernate.initialize(equipe.getColaboradores());

        Set<Usuario> colaboradoresAtualizados = new HashSet<>();
        for (Long colaboradorId : equipeDTO.getColaboradoresIds()) {
            Usuario colaborador = usuarioRepository.findById(colaboradorId)
                    .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado com ID: " + colaboradorId));

            colaboradoresAtualizados.add(colaborador);
        }

        for (Usuario colaborador : equipe.getColaboradores()) {
            if (!colaboradoresAtualizados.contains(colaborador)) {
                usuarioService.updateEquipe(colaborador.getId(), null);
            }
        }

        configurarEquipe(equipe, equipeDTO);
        equipe.setDataCriacao(equipe.getDataCriacao());
        equipeRepository.save(equipe);

        for (Usuario colaborador : colaboradoresAtualizados) {
            usuarioService.updateEquipe(colaborador.getId(), equipe.getId());
        }

        equipeDTO.setId(equipe.getId());
        equipeDTO.setGestor(equipe.getGestor());
        equipeDTO.setDataCriacao(equipe.getDataCriacao());
        equipeDTO.setColaboradores(new ArrayList<>(colaboradoresAtualizados));
        return equipeDTO;
    }

    private EquipeDTO converterEquipe (Equipe equipe) {
        Hibernate.initialize(equipe.getColaboradores());

        EquipeDTO equipeConvertida = new EquipeDTO();
        equipeConvertida.setId(equipe.getId());
        equipeConvertida.setNome(equipe.getNome());
        equipeConvertida.setDataCriacao(equipe.getDataCriacao());
        equipeConvertida.setDataEdicao(equipe.getDataEdicao());
        equipeConvertida.setGestorId(equipe.getGestor().getId());
        equipeConvertida.setGestor(equipe.getGestor());

        for (Usuario colaborador : equipe.getColaboradores()) {
            equipeConvertida.addColaborador(colaborador);
        }
        return equipeConvertida;
    }

    public EquipeDTO getById(Long equipeId) {
        Equipe equipe = equipeRepository.findById(equipeId).
                orElseThrow(() -> new EntityNotFoundException("Equipe não encontrada com ID: " + equipeId));

        return converterEquipe(equipe);
    }

    public List<EquipeDTO> getByGestorId(Long gestorId) {
        List<Equipe> equipes = equipeRepository.findByGestorId(gestorId);

        if (equipes.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma equipe encontrada para o gestor ID: " + gestorId);
        }

        return equipes.stream().map(this::converterEquipe).collect(Collectors.toList());
    }

    public EquipeDTO getByColaboradorId(Long colaboradorId) {
        Usuario colaboradorRecuperado = usuarioRepository.findById(colaboradorId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + colaboradorId));

        Equipe equipe = colaboradorRecuperado.getEquipe();
        if (equipe == null) {
            throw new EntityNotFoundException("Colaborador de ID: " + colaboradorId + ", não está associado a nenhuma equipe.");
        }

        Equipe equipeRecuperada = equipeRepository.findById(equipe.getId())
                .orElseThrow(() -> new EntityNotFoundException("Equipe não encontrada com ID: " + equipe.getId()));

        return converterEquipe(equipeRecuperada);
    }

    public String delete(Long equipeId){
        Equipe equipe = equipeRepository.findById(equipeId)
                .orElseThrow(() -> new EntityNotFoundException("Equipe não encontrada com ID: " + equipeId));

        for (Usuario colaborador : equipe.getColaboradores()) {
            usuarioService.updateEquipe(colaborador.getId(), null);
        }

        equipeRepository.deleteById(equipeId);
        return "Equipe deletada com sucesso!";
    }
}
