package com.feedbackFusion.service;

import com.feedbackFusion.dto.ConquistaDTO;
import com.feedbackFusion.model.Conquista;
import com.feedbackFusion.repository.ConquistaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConquistaService {
    @Autowired
    private ConquistaRepository repository;

    private void configurarConquista(Conquista conquista, ConquistaDTO conquistaDTO){
        conquista.setGestorId(conquistaDTO.getGestorId());
        conquista.setColaboradorId(conquistaDTO.getColaboradorId());
        conquista.setDataAtribuicao(conquistaDTO.getDataAtribuicao());
        conquista.setSeloId(conquistaDTO.getSeloId());
    }

    public ConquistaDTO create(ConquistaDTO conquistaDTO){
        Conquista conquista = new Conquista();
        configurarConquista(conquista, conquistaDTO);

        repository.save(conquista);
        conquistaDTO.setId(conquista.getId());
        return conquistaDTO;
    }

    private ConquistaDTO converterConquista(Conquista conquista) {
        ConquistaDTO conquistaConvertida = new ConquistaDTO();
        conquistaConvertida.setId(conquista.getId());
        conquistaConvertida.setDataAtribuicao(conquista.getDataAtribuicao());
        conquistaConvertida.setGestorId(conquista.getGestorId());
        conquistaConvertida.setColaboradorId(conquista.getColaboradorId());
        conquistaConvertida.setSeloId(conquista.getSeloId());
        return conquistaConvertida;
    }

    public List<ConquistaDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::converterConquista)
                .collect(Collectors.toList());
    }

    public List<ConquistaDTO> getByColaboradorId(Long colaboradorId) {
        List<Conquista> conquistas = repository.findByColaboradorId(colaboradorId);

        if (conquistas.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma conquista encontrada para o colaborador ID: " + colaboradorId);
        }

        return conquistas.stream()
                .map(this::converterConquista)
                .collect(Collectors.toList());
    }

    public List<ConquistaDTO> getByGestorIdAndColaboradorId(Long gestorId, Long colaboradorId) {
        List<Conquista> conquistas = repository.findByGestorIdAndColaboradorId(gestorId, colaboradorId);

        if (conquistas.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma conquista encontrada para o gestor ID: " + gestorId + " e colaborador ID: " + colaboradorId);
        }

        return conquistas.stream().map(this::converterConquista).collect(Collectors.toList());
    }

    public String delete(Long conquistaId){
        repository.deleteById(conquistaId);
        return "Conquista deletada com sucesso!";
    }
}