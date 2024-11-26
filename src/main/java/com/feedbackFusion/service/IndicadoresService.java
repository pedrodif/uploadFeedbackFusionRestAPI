package com.feedbackFusion.service;

import com.feedbackFusion.dto.EquipeDTO;
import com.feedbackFusion.dto.TarefaDTO;
import com.feedbackFusion.model.Usuario;
import org.springframework.stereotype.Service;
import com.feedbackFusion.dto.EquipeIndicadoresDTO;
import com.feedbackFusion.dto.ColaboradorIndicadoresDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

@Service
public class IndicadoresService {
    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private EquipeService equipeService;

    @Autowired
    private ConquistaService conquistaService;

    private double formatarComDuasCasasDecimais(double valor) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##", symbols);
        return Double.parseDouble(df.format(valor));
    }

    public int numeroFeedbacksEquipe(Long equipeId){
        EquipeDTO equipeRecuperada = equipeService.getById(equipeId);

        if (equipeRecuperada == null || equipeRecuperada.getColaboradores().isEmpty()) {
            return 0;
        }

        return equipeRecuperada.getColaboradores().stream()
                .mapToInt(colaborador -> feedbackService.getByColaboradorId(colaborador.getId()).size())
                .sum();
    }

    public double mediaFeedbacksEquipe(Long equipeId){
        EquipeDTO equipeRecuperada = equipeService.getById(equipeId);

        if (equipeRecuperada == null || equipeRecuperada.getColaboradores().isEmpty()) {
            return 0.0;
        }

        int totalFeedbacks = equipeRecuperada.getColaboradores().stream()
                .mapToInt(colaborador -> feedbackService.getByColaboradorId(colaborador.getId()).size())
                .sum();

        double media = (double) totalFeedbacks / equipeRecuperada.getColaboradores().size();
        return formatarComDuasCasasDecimais(media);
    }

    public double taxaEntregasNoPrazoColaborador(Long idColaborador){
        List<TarefaDTO> tarefasRecuperadas = tarefaService.getByColaboradorId(idColaborador);

        if (tarefasRecuperadas.isEmpty()) {
            return 0.0;
        }

        int tarefasConcluidasNoPrazo = tarefasRecuperadas.stream()
                .mapToInt(tarefa -> tarefa.isStatusConclusao() ? 1 : 0)
                .sum();

        double taxa = ((double) tarefasConcluidasNoPrazo / tarefasRecuperadas.size()) * 100;
        return formatarComDuasCasasDecimais(taxa);
    }

    public double taxaEntregasNoPrazoEquipe(Long equipeId) {
        EquipeDTO equipeRecuperada = equipeService.getById(equipeId);

        if (equipeRecuperada == null || equipeRecuperada.getColaboradores().isEmpty()) {
            return 0.0;
        }

        List<TarefaDTO> tarefasRecuperadas = new ArrayList<>();
        for (Usuario colaborador : equipeRecuperada.getColaboradores()) {
            tarefasRecuperadas.addAll(tarefaService.getByColaboradorId(colaborador.getId()));
        }

        if (tarefasRecuperadas.isEmpty()) {
            return 0.0;
        }

        int tarefasConcluidasNoPrazo = tarefasRecuperadas.stream()
                .mapToInt(tarefa -> tarefa.isStatusConclusao() ? 1 : 0)
                .sum();

        double taxa = ((double) tarefasConcluidasNoPrazo / tarefasRecuperadas.size()) * 100;
        return formatarComDuasCasasDecimais(taxa);
    }

    public double taxaPontuacaoEquipe(Long equipeId){
        EquipeDTO equipeRecuperada = equipeService.getById(equipeId);

        if (equipeRecuperada == null || equipeRecuperada.getColaboradores().isEmpty()) {
            return 0.0;
        }

        List<TarefaDTO> tarefasRecuperadas = new ArrayList<>();
        for (Usuario colaborador : equipeRecuperada.getColaboradores()) {
            tarefasRecuperadas.addAll(tarefaService.getByColaboradorId(colaborador.getId()));
        }

        if (tarefasRecuperadas.isEmpty()) {
            return 0.0;
        }

        int pontuacaoObtida = tarefasRecuperadas.stream()
                .mapToInt(TarefaDTO::getPontuacaoObtida)
                .sum();

        int pontuacaoAtribuida = tarefasRecuperadas.stream()
                .mapToInt(TarefaDTO::getPontuacao)
                .sum();

        if (pontuacaoAtribuida == 0) {
            return 0.0;
        }

        double taxa = ((double) pontuacaoObtida / pontuacaoAtribuida) * 100;
        return formatarComDuasCasasDecimais(taxa);
    }

    public double taxaPontuacaoColaborador(Long idColaborador){
        List<TarefaDTO> tarefasRecuperadas = tarefaService.getByColaboradorId(idColaborador);

        if (tarefasRecuperadas.isEmpty()) {
            return 0.0;
        }

        int pontuacaoObtida = tarefasRecuperadas.stream()
                .mapToInt(TarefaDTO::getPontuacaoObtida)
                .sum();

        int pontuacaoAtribuida = tarefasRecuperadas.stream()
                .mapToInt(TarefaDTO::getPontuacao)
                .sum();

        if (pontuacaoAtribuida == 0) {
            return 0.0;
        }

        double taxa = ((double) pontuacaoObtida / pontuacaoAtribuida) * 100;
        return formatarComDuasCasasDecimais(taxa);
    }

    public double taxaMonitoresEquipe(Long equipeId){
        EquipeDTO equipeRecuperada = equipeService.getById(equipeId);

        if (equipeRecuperada == null || equipeRecuperada.getColaboradores().isEmpty()) {
            return 0.0;
        }

        int contagemMonitores = equipeRecuperada.getColaboradores().stream()
                .mapToInt(colaborador -> colaborador.isStatusMonitor() ? 1 : 0)
                .sum();

        double taxa = ((double) contagemMonitores / equipeRecuperada.getColaboradores().size()) * 100;
        return formatarComDuasCasasDecimais(taxa);
    }

    public double taxaColaboradoresSelo(Long equipeId) {
        EquipeDTO equipeRecuperada = equipeService.getById(equipeId);

        if (equipeRecuperada == null || equipeRecuperada.getColaboradores().isEmpty()) {
            return 0.0;
        }

        int totalConquistas = equipeRecuperada.getColaboradores().stream()
                .mapToInt(colaborador -> !conquistaService.getByColaboradorId(colaborador.getId()).isEmpty() ? 1 : 0)
                .sum();

        double taxa = ((double) totalConquistas / equipeRecuperada.getColaboradores().size()) * 100;
        return formatarComDuasCasasDecimais(taxa);
    }

    public ColaboradorIndicadoresDTO getByColaboradorId(Long colaboradorId){
        ColaboradorIndicadoresDTO colaboradorIndicadores = new ColaboradorIndicadoresDTO();
        colaboradorIndicadores.setColaboradorId(colaboradorId);
        colaboradorIndicadores.setTaxaPontuacao(this.taxaPontuacaoColaborador(colaboradorId));
        colaboradorIndicadores.setTaxaEntregasNoPrazo(this.taxaEntregasNoPrazoColaborador(colaboradorId));
        return colaboradorIndicadores;
    }

    public EquipeIndicadoresDTO getByEquipeId(Long equipeId) {
        EquipeIndicadoresDTO equipeIndicadores = new EquipeIndicadoresDTO();
        equipeIndicadores.setEquipeId(equipeId);
        equipeIndicadores.setMediaFeedbacks(this.mediaFeedbacksEquipe(equipeId));
        equipeIndicadores.setNumeroFeedbacks(this.numeroFeedbacksEquipe(equipeId));
        equipeIndicadores.setTaxaEntregasNoPrazo(this.taxaEntregasNoPrazoEquipe(equipeId));
        equipeIndicadores.setTaxaPontuacao(this.taxaPontuacaoEquipe(equipeId));
        equipeIndicadores.setTaxaMonitores(this.taxaMonitoresEquipe(equipeId));
        equipeIndicadores.setTaxaColaboradoresSelo(this.taxaColaboradoresSelo(equipeId));
        return equipeIndicadores;
    }
}
