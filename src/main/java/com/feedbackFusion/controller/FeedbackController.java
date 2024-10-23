package com.feedbackFusion.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.CONTENT_TYPE, "application/json").body(createdFeedback);
    }

    @PutMapping("/{feedbackId}")
    public ResponseEntity<FeedbackDTO> update(@PathVariable("feedbackId") Long feedbackId, @RequestBody FeedbackDTO feedbackDTO){
        FeedbackDTO updatedFeedback = feedbackService.update(feedbackId, feedbackDTO);
        return ResponseEntity.ok(updatedFeedback);
    }

    @GetMapping
    public List<FeedbackDTO> getAll(){
        return feedbackService.getAll();
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
    public String delete(@PathVariable("feedbackId") Long feedbackId){
        return feedbackService.delete(feedbackId);
    }
}
