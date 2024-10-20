package com.feedbackFusion.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private String titulo, descricao;
    private Long id, gestorId, colaboradorId;
    private LocalDate dataCriacao, dataEdicao;

    public Long getId() {
        return this.id;
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

    public LocalDate getDataEdicao() {
        return this.dataEdicao;
    }

    public void setDataEdicao(LocalDate dataEdicao) {
        this.dataEdicao = dataEdicao;
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

    public Long getGestorId(){
        return this.gestorId;
    }

    public void setGestorId(Long gestorId){
        this.gestorId = gestorId;
    }

    public Long getColaboradorId(){
        return this.colaboradorId;
    }

    public void setColaboradorId(Long colaboradorId) {
        this.colaboradorId = colaboradorId;
    }
}
