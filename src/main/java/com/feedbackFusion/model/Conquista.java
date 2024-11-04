package com.feedbackFusion.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "conquista")
public class Conquista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private Long gestorId, colaboradorId, seloId;

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
