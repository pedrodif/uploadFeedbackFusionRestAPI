package com.feedbackFusion.dto;
import java.util.ArrayList;
import java.util.List;

import com.feedbackFusion.service.*;

public class IndicadoresDTO {

    public FeedbackService feedbackService;
    public EquipeService equipeService;
    public TarefaService tarefaService;

    public double mediaFeedbacks(){ 
         return (double) (feedbackService.getAll().size() / equipeService.getAllColaboradores().size());
    }
    public double mediaTarefasNoPrazoColaborador(){
        List<TarefaDTO> tarefas = tarefaService.getAll();
        List<TarefaDTO> tarefasNoPrazo = new ArrayList<>();
        for(TarefaDTO tarefa : tarefas){
            if(tarefa.getDataConclusao().isBefore(tarefa.getDataPrazo())){
                tarefasNoPrazo.add(tarefa);
            }
        }
        return (double) (tarefasNoPrazo.size() / equipeService.getAllColaboradores().size());
    }
}
