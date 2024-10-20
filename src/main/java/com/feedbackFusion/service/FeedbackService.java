package com.feedbackFusion.service;

import com.feedbackFusion.model.Feedback;
import com.feedbackFusion.dto.FeedbackDTO;
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

    private void configurarFeedback(Feedback feedback, FeedbackDTO feedbackDTO){
        feedback.setDataCriacao(feedbackDTO.getDataCriacao());
        feedback.setDataEdicao(feedbackDTO.getDataEdicao());
        feedback.setTitulo(feedbackDTO.getTitulo());
        feedback.setDescricao(feedbackDTO.getDescricao());
        feedback.setGestorId(feedbackDTO.getGestorId());
        feedback.setColaboradorId(feedbackDTO.getColaboradorId());
    }

    public FeedbackDTO create(FeedbackDTO FeedbackDTO){
        Feedback feedback = new Feedback();
        configurarFeedback(feedback, FeedbackDTO);
        repository.save(feedback);
        FeedbackDTO.setId(feedback.getId());
        return FeedbackDTO;
    }

    public FeedbackDTO update(Long feedbackId, FeedbackDTO feedbackDTO){
        Feedback feedback = repository.findById(feedbackId)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum feedback encontrado com o ID: " + feedbackId));

        configurarFeedback(feedback, feedbackDTO);
        repository.save(feedback);
        feedbackDTO.setId(feedback.getId());
        return feedbackDTO;
    }

    private FeedbackDTO converterFeedback (Feedback feedback) {
        FeedbackDTO feedbackConvertido = new FeedbackDTO();
        feedbackConvertido.setId(feedback.getId());
        feedbackConvertido.setDataCriacao(feedback.getDataCriacao());
        feedbackConvertido.setDataEdicao(feedback.getDataEdicao());
        feedbackConvertido.setTitulo(feedback.getTitulo());
        feedbackConvertido.setDescricao(feedback.getDescricao());
        feedbackConvertido.setGestorId(feedback.getGestorId());
        feedbackConvertido.setColaboradorId(feedback.getColaboradorId());
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