package com.feedbackFusion.dto;

public class EquipeIndicadoresDTO {
    private Long equipeId;
    private double mediaFeedbacks;
    private double taxaEntregasNoPrazo;
    private double taxaPontuacao;
    private double taxaMonitores;
    private double taxaColaboradoresSelo;
    private int numeroFeedbacks;

    public Long getEquipeId() {
        return this.equipeId;
    }

    public void setEquipeId(Long equipeId) {
        this.equipeId = equipeId;
    }

    public double getMediaFeedbacks() {
        return this.mediaFeedbacks;
    }

    public void setMediaFeedbacks(double mediaFeedbacks) {
        this.mediaFeedbacks = mediaFeedbacks;
    }

    public double getTaxaEntregasNoPrazo() {
        return this.taxaEntregasNoPrazo;
    }

    public void setTaxaEntregasNoPrazo(double taxaEntregasNoPrazo) {
        this.taxaEntregasNoPrazo = taxaEntregasNoPrazo;
    }

    public double getTaxaPontuacao() {
        return this.taxaPontuacao;
    }

    public void setTaxaPontuacao(double taxaPontuacao) {
        this.taxaPontuacao = taxaPontuacao;
    }

    public double getTaxaMonitores() {
        return this.taxaMonitores;
    }

    public void setTaxaMonitores(double taxaMonitores) {
        this.taxaMonitores = taxaMonitores;
    }

    public double getTaxaColaboradoresSelo() {
        return this.taxaColaboradoresSelo;
    }

    public void setTaxaColaboradoresSelo(double taxaColaboradoresSelo) {
        this.taxaColaboradoresSelo = taxaColaboradoresSelo;
    }

    public int getNumeroFeedbacks() {
        return this.numeroFeedbacks;
    }

    public void setNumeroFeedbacks(int numeroFeedbacks) {
        this.numeroFeedbacks = numeroFeedbacks;
    }
}
