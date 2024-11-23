package com.feedbackFusion.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class ConquistaDTO {
    private Long id, gestorId, colaboradorId, seloId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataAtribuicao;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getSeloId(){
        return this.seloId;
    }

    public void setSeloId(Long seloId){
        this.seloId = seloId;
    }
}