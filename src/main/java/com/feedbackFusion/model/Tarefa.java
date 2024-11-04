package com.feedbackFusion.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tarefa")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private Long gestorId, colaboradorId;
    private boolean statusConclusao = false;
    private int pontuacao = 0, pontuacaoObtida = 0;
    private String documentos, comentariosGestor, titulo, descricao;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCriacao, dataPrazo, dataEdicao, dataConclusao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDataCriacao() {
        return this.dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataPrazo() {
        return this.dataPrazo;
    }

    public void setDataPrazo(LocalDate dataPrazo) {
        this.dataPrazo = dataPrazo;
    }

    public LocalDate getDataEdicao() {
        return this.dataEdicao;
    }

    public void setDataEdicao(LocalDate dataEdicao) {
        this.dataEdicao = dataEdicao;
    }

    public LocalDate getDataConclusao() {
        return this.dataConclusao;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public boolean isStatusConclusao() {
        return this.statusConclusao;
    }

    public void setStatusConclusao(boolean statusConclusao) {
        this.statusConclusao = statusConclusao;
    }

    public int getPontuacao() {
        return this.pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getPontuacaoObtida() {
        return this.pontuacaoObtida;
    }

    public void setPontuacaoObtida(int pontuacaoObtida) {
        this.pontuacaoObtida = pontuacaoObtida;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDocumentos() {
        return this.documentos;
    }

    public void setDocumentos(String documentos) {
        this.documentos = documentos;
    }

    public String getComentariosGestor() {
        return this.comentariosGestor;
    }

    public void setComentariosGestor(String comentariosGestor) {
        this.comentariosGestor = comentariosGestor;
    }

    public Long getGestorId() {
        return this.gestorId;
    }

    public void setGestorId(Long gestorId) {
        this.gestorId = gestorId;
    }

    public Long getColaboradorId() {
        return this.colaboradorId;
    }

    public void setColaboradorId(Long colaboradorId) {
        this.colaboradorId = colaboradorId;
    }
}