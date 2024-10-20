package com.feedbackFusion.controller;

import org.springframework.http.MediaType;
import com.feedbackFusion.dto.FeedbackDTO;
import com.feedbackFusion.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/feedbacks", produces = MediaType.APPLICATION_JSON_VALUE)
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping
    @ResponseBody
    public FeedbackDTO create(@RequestBody FeedbackDTO feedbackDTO){
        return feedbackService.create(feedbackDTO);
    }

    @PutMapping("/{feedbackId}")
    @ResponseBody
    public FeedbackDTO update(@PathVariable("feedbackId") Long feedbackId, @RequestBody FeedbackDTO feedbackDTO){
        return feedbackService.update(feedbackId, feedbackDTO);
    }

    @GetMapping
    @ResponseBody
    public List<FeedbackDTO> getAll(){
        return feedbackService.getAll();
    }

    @GetMapping("/{feedbackId}")
    @ResponseBody
    public FeedbackDTO getById(@PathVariable("feedbackId") Long feedbackId){
        return feedbackService.getById(feedbackId);
    }

    @GetMapping("colaborador/{colaboradorId}")
    @ResponseBody
    public List<FeedbackDTO> getByColaboradorId(@PathVariable("colaboradorId") Long colaboradorId){
        return feedbackService.getByColaboradorId(colaboradorId);
    }

    @GetMapping("/gestor/{gestorId}/colaborador/{colaboradorId}")
    @ResponseBody
    public List<FeedbackDTO> getByGestorIdAndColaboradorId(@PathVariable("gestorId") Long gestorId, @PathVariable("colaboradorId") Long colaboradorId){
        return feedbackService.getByGestorIdAndColaboradorId(gestorId, colaboradorId);
    }

    @DeleteMapping("/{feedbackId}")
    @ResponseBody
    public String delete(@PathVariable("feedbackId") Long feedbackId){
        return feedbackService.delete(feedbackId);
    }
}
