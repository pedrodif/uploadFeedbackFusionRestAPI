package com.feedbackFusion.dto;
import java.util.ArrayList;
import java.util.List;

import com.feedbackFusion.service.*;

public class IndicadoresDTO {
    public FeedbackService feedbackService = new FeedbackService();
    public EquipeService equipeService = new EquipeService();

    public double mediaFeedbacks(){ 
         return (double) (feedbackService.getAll().size() / equipeService.getAllColaboradores().size());
    }
}
