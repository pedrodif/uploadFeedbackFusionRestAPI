package com.feedbackFusion.controller;

import org.springframework.http.MediaType;
import com.feedbackFusion.dto.FeedbackDTO;
import com.feedbackFusion.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/feedbacks", produces = MediaType.APPLICATION_JSON_VALUE)
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackDTO> create(@RequestBody FeedbackDTO feedbackDTO){
        FeedbackDTO createdFeedback = feedbackService.create(feedbackDTO);
        return ResponseEntity.ok(createdFeedback);
    }

    @PutMapping("/{feedbackId}")
    public ResponseEntity<FeedbackDTO> update(@PathVariable("feedbackId") Long feedbackId, @RequestBody FeedbackDTO feedbackDTO){
        FeedbackDTO updatedFeedback = feedbackService.update(feedbackId, feedbackDTO);
        return ResponseEntity.ok(updatedFeedback);
    }

    @GetMapping
    public ResponseEntity<List<FeedbackDTO>> getAll(){
        List<FeedbackDTO> feedbacks = feedbackService.getAll();
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/{feedbackId}")
    public ResponseEntity<FeedbackDTO> getById(@PathVariable("feedbackId") Long feedbackId) {
        FeedbackDTO feedback = feedbackService.getById(feedbackId);
        return ResponseEntity.ok(feedback);
    }

    @GetMapping("/colaborador/{colaboradorId}")
    public ResponseEntity<List<FeedbackDTO>> getByColaboradorId(@PathVariable("colaboradorId") Long colaboradorId) {
        List<FeedbackDTO> feedbacks = feedbackService.getByColaboradorId(colaboradorId);
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/gestor/{gestorId}/colaborador/{colaboradorId}")
    public ResponseEntity<List<FeedbackDTO>> getByGestorIdAndColaboradorId(@PathVariable("gestorId") Long gestorId, @PathVariable("colaboradorId") Long colaboradorId){
        List<FeedbackDTO> feedbacks = feedbackService.getByGestorIdAndColaboradorId(gestorId, colaboradorId);
        return ResponseEntity.ok(feedbacks);
    }

    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<String> delete(@PathVariable("feedbackId") Long feedbackId){
        String feedbackDeletado =  feedbackService.delete(feedbackId);
        return ResponseEntity.ok(feedbackDeletado);
    }
}
