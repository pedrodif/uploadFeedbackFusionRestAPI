package com.feedbackFusion.service;

import com.feedbackFusion.model.Feedback;
import com.feedbackFusion.dto.FeedbackDTO;
import com.feedbackFusion.model.Usuario;
import com.feedbackFusion.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import com.feedbackFusion.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private void configurarFeedback(Feedback feedback, FeedbackDTO feedbackDTO){
        feedback.setDataEdicao(feedbackDTO.getDataEdicao());
        feedback.setTitulo(feedbackDTO.getTitulo());
        feedback.setDescricao(feedbackDTO.getDescricao());
        feedback.setColaboradorId(feedbackDTO.getColaboradorId());
    }

    public FeedbackDTO create(FeedbackDTO feedbackDTO){
        Feedback feedback = new Feedback();
        configurarFeedback(feedback, feedbackDTO);
        feedback.setDataCriacao(feedbackDTO.getDataCriacao());

        Usuario usuario = usuarioRepository.findById(feedbackDTO.getGestorId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + feedbackDTO.getGestorId()));

        feedback.setGestor(usuario);

        repository.save(feedback);
        feedbackDTO.setGestor(feedback.getGestor());
        feedbackDTO.setId(feedback.getId());
        return feedbackDTO;
    }

    public FeedbackDTO update(Long feedbackId, FeedbackDTO feedbackDTO){
        Feedback feedback = repository.findById(feedbackId)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum feedback encontrado com o ID: " + feedbackId));

        configurarFeedback(feedback, feedbackDTO);
        feedback.setDataCriacao(feedback.getDataCriacao());
        repository.save(feedback);

        feedbackDTO.setId(feedback.getId());
        feedbackDTO.setDataCriacao(feedback.getDataCriacao());

        return feedbackDTO;
    }

    private FeedbackDTO converterFeedback (Feedback feedback) {
        FeedbackDTO feedbackConvertido = new FeedbackDTO();
        feedbackConvertido.setId(feedback.getId());
        feedbackConvertido.setDataCriacao(feedback.getDataCriacao());
        feedbackConvertido.setDataEdicao(feedback.getDataEdicao());
        feedbackConvertido.setTitulo(feedback.getTitulo());
        feedbackConvertido.setDescricao(feedback.getDescricao());
        feedbackConvertido.setColaboradorId(feedback.getColaboradorId());
        feedbackConvertido.setGestorId(feedback.getGestor().getId());
        feedbackConvertido.setGestor(feedback.getGestor());
        return feedbackConvertido;
    }

    public List<FeedbackDTO> getAll() {
        return repository.findAll().stream().map(this::converterFeedback).collect(Collectors.toList());
    }

    public List<FeedbackDTO> getByColaboradorId(Long colaboradorId) {
        List<Feedback> feedbacks = repository.findByColaboradorId(colaboradorId);

        if (feedbacks.isEmpty()) {
            throw new EntityNotFoundException("Nenhum feedback encontrado para o colaborador ID: " + colaboradorId);
        }
        return feedbacks.stream().map(this::converterFeedback).collect(Collectors.toList());
    }

    public List<FeedbackDTO> getByGestorIdAndColaboradorId(Long gestorId, Long colaboradorId) {
        List<Feedback> feedbacks = repository.findByGestorIdAndColaboradorId(gestorId, colaboradorId);

        if (feedbacks.isEmpty()) {
            throw new EntityNotFoundException("Nenhum feedback encontrado para o gestor ID: " + gestorId + " e colaborador ID: " + colaboradorId);
        }

        return feedbacks.stream().map(this::converterFeedback).collect(Collectors.toList());
    }

    public FeedbackDTO getById(Long feedbackId){
        Feedback feedbackRecuperado = repository.findById(feedbackId)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum feedback encontrado com o ID: " + feedbackId));

        return converterFeedback(feedbackRecuperado);
    }

    public String delete(Long feedbackId){
        repository.deleteById(feedbackId);
        return "Feedback deletado com sucesso!";
    }
}