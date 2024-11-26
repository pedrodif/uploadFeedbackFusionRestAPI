package com.feedbackFusion.dto;

public class ColaboradorIndicadoresDTO {
    private Long colaboradorId;
    private double taxaEntregasNoPrazo;
    private double taxaPontuacao;

    public Long getColaboradorId() {
        return this.colaboradorId;
    }

    public void setColaboradorId(Long colaboradorId) {
        this.colaboradorId = colaboradorId;
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
}
