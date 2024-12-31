package com.feedbackFusion.dto;
import java.util.ArrayList;
import java.util.List;

import com.feedbackFusion.model.Conquista;
import com.feedbackFusion.model.Tarefa;
import com.feedbackFusion.model.Usuario;
import com.feedbackFusion.repository.ConquistaRepository;
import com.feedbackFusion.repository.TarefaRepository;
import com.feedbackFusion.service.*;
public class IndicadoresDTO {

    private FeedbackService feedbackService;
    private EquipeService equipeService;
    private TarefaService tarefaService;
    private ConquistaRepository conquistaRepository;
    private TarefaRepository tarefaRepository;

    public void setFeedbackService(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }
    public EquipeService getEquipeService() {
        return equipeService;
    }
    public void setEquipeService(EquipeService equipeService) {
        this.equipeService = equipeService;
    }
    public TarefaService getTarefaService() {
        return tarefaService;
    }
    public void setTarefaService(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }
    public ConquistaRepository getConquistaRepository() {
        return conquistaRepository;
    }
    public void setConquistaRepository(ConquistaRepository conquistaRepository) {
        this.conquistaRepository = conquistaRepository;
    }
    public TarefaRepository getTarefaRepository() {
        return tarefaRepository;
    }
    public void setTarefaRepository(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

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
    public double mediaColaboradoresSelo(Long idEquipe){
        List<Usuario> colaboradores = equipeService.getAllColaboradores();
        List<Conquista> conquistas;
        int colaboradorComSelo = 0;
        for(Usuario colaborador : colaboradores){
            conquistas = conquistaRepository.findByColaboradorId(colaborador.getId());
            if(conquistas.isEmpty()){}
            else{
                colaboradorComSelo++;
            }
        }
        return (double) colaboradorComSelo/colaboradores.size();
    }
    public double mediacolaboradoresMonitor(){
        List<Usuario> colaboradores = equipeService.getAllColaboradores();
        int colaboradoresMonitor = 0;
        for(Usuario colaborador: colaboradores){
            if(colaborador.isStatusMonitor()){
                colaboradoresMonitor++;
            }
        }
        return (double) colaboradoresMonitor/colaboradores.size();
    }
    public double MediaPontuacaoColaborador(Long idColaborador){
        List<Tarefa> tarefas = tarefaRepository.findByColaboradorId(idColaborador);
        double pontuacaoTotal = 0.0;
        double pontuacaoObtida= 0.0;

        for(Tarefa tarefa: tarefas){
            pontuacaoTotal += tarefa.getPontuacao();
            pontuacaoObtida += tarefa.getPontuacaoObtida();
        }
        return pontuacaoObtida/pontuacaoTotal;
    }

    public double MediaPontuacaoEquipe(Long idEquipe){
        List<Usuario> colaboradores = equipeService.getAllColaboradores();
        double sum = 0;
        for(Usuario colaborador: colaboradores){
            sum += MediaPontuacaoColaborador(colaborador.getId());
        }
        return (double) sum/colaboradores.size();
    }
}

