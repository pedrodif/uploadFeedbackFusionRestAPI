package com.feedbackFusion.dto;

import com.fasterxml.jackson.annotation.*;
import com.feedbackFusion.model.Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EquipeDTO {
    private String nome;
    private Usuario gestor;
    private Long id, gestorId;
    private List<Long> colaboradoresIds;
    private List<Usuario> colaboradores = new ArrayList<>();

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCriacao, dataEdicao;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGestorId() {
        return this.gestorId;
    }

    public void setGestorId(Long gestorId) {
        this.gestorId = gestorId;
    }

    public Usuario getGestor() {
        return this.gestor;
    }

    public void setGestor(Usuario gestor) {
        this.gestor = gestor;
    }


    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Long> getColaboradoresIds() {
        return this.colaboradoresIds;
    }

    public void setColaboradoresIds(List<Long> colaboradoresIds) {
        this.colaboradoresIds = colaboradoresIds;
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

    public void addColaborador(Usuario usuario) {
        this.colaboradores.add(usuario);
    }

    public void removeColaborador(Usuario usuario) {
        this.colaboradores.remove(usuario);
    }

    public List<Usuario> getColaboradores() {
        return this.colaboradores;
    }

    public void setColaboradores(List<Usuario> colaboradores) {
        this.colaboradores = colaboradores;
    }
}
