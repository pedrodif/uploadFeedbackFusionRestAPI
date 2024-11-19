package com.feedbackFusion.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.feedbackFusion.model.Tarefa;
import com.feedbackFusion.model.Usuario;

import java.time.LocalDate;

public class SolicitacaoAjudaDTO {
    private Tarefa tarefa;
    private Usuario colaborador;
    private Long id, monitorId, colaboradorId, tarefaId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataSolicitacao;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMonitorId() {
        return this.monitorId;
    }

    public void setMonitorId(Long monitorId) {
        this.monitorId = monitorId;
    }

    public Long getColaboradorId() {
        return this.colaboradorId;
    }

    public void setColaboradorId(Long colaboradorId) {
        this.colaboradorId = colaboradorId;
    }

    public Usuario getColaborador() {
        return this.colaborador;
    }

    public void setColaborador(Usuario colaborador) {
        this.colaborador = colaborador;
    }

    public Long getTarefaId() {
        return this.tarefaId;
    }

    public void setTarefaId(Long tarefaId) {
        this.tarefaId = tarefaId;
    }

    public Tarefa getTarefa() {
        return this.tarefa;
    }

    public void setTarefa(Tarefa tarefa) {
        this.tarefa = tarefa;
    }

    public LocalDate getDataSolicitacao() {
        return this.dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }
}
