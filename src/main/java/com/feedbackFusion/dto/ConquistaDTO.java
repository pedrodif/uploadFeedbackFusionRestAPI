package com.feedbackFusion.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class ConquistaDTO {
    private String nome, descricao, tipo;
    private Long id, gestorId, colaboradorId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataAtribuicao;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataAtribuicao() {
        return this.dataAtribuicao;
    }

    public void setDataAtribuicao(LocalDate dataAtribuicao) {
        this.dataAtribuicao = dataAtribuicao;
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