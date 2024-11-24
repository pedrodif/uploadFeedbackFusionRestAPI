package com.feedbackFusion.dto;
import java.util.ArrayList;
import java.util.List;

import com.feedbackFusion.model.Usuario;
import com.feedbackFusion.service.*;
public class IndicadoresDTO {

    public FeedbackService feedbackService;
    public EquipeService equipeService;
    public TarefaService tarefaService;

    public double mediaFeedbacks(){ 
         return (double) (feedbackService.getAll().size() / equipeService.getAllColaboradores().size());
    }
   public double mediaFeedbacksNoPrazoColaborador(Long idColaborador){
    List<TarefaDTO> tarefasColaborador = tarefaService.getByColaboradorId(idColaborador);
    List<TarefaDTO> tarefasConcluidasColaborador = new ArrayList<>();
    for(TarefaDTO tarefa : tarefasColaborador){
        if(tarefa.isStatusConclusao()){
            tarefasConcluidasColaborador.add(tarefa);
            }
        }
        return (double) (tarefasConcluidasColaborador.size() / tarefasColaborador.size());
   }
   
   public double mediaFeedbacksNoPrazoEquipe(Long idEquipe){
    List<Usuario> colaboradores = equipeService.getAllColaboradores();
    double sum = 0;
    for(Usuario colaborador: colaboradores){
        sum += mediaFeedbacksNoPrazoColaborador(colaborador.getId());
    }
    return (double) sum/colaboradores.size();
    }
}
