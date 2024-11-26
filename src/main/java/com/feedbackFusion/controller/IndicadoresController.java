package com.feedbackFusion.controller;

import com.feedbackFusion.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/indicadores", produces = MediaType.APPLICATION_JSON_VALUE)
public class IndicadoresController {
    @Autowired
    private FeedbackService feedbackService;


}
