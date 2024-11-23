package com.feedbackFusion.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipe")
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;
    private String nome;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataCriacao, dataEdicao;

    @JsonManagedReference
    @OneToMany(mappedBy = "equipe", fetch = FetchType.LAZY)
    private List<Usuario> colaboradores = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "gestor_id", referencedColumnName = "id")
    private Usuario gestor;

    public Long getId() {
        return id;
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

    public Usuario getGestor() {
        return this.gestor;
    }

    public void setGestor(Usuario gestor) {
        this.gestor = gestor;
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

    public List<Usuario> getColaboradores() {
        return this.colaboradores;
    }

    public void setColaboradores(List<Usuario> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public void addColaborador(Usuario colaborador) {
        if (!this.colaboradores.contains(colaborador)) {
            this.colaboradores.add(colaborador);
        } else {
            throw new IllegalStateException("Colaborador já está na equipe");
        }
    }

    public void removeColaborador(Usuario usuario) {
        this.colaboradores.remove(usuario);
    }
}