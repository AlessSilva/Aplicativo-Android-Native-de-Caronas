package com.example.appcaronamobile.Model;

public class Carona {

    private int vagas;
    private String tipoveiculo;
    private String horario;
    private String destino;
    private boolean ajuda;

    public Carona(int vagas, String tipoveiculo, String horario, String destino, boolean ajuda) {
        this.vagas = vagas;
        this.tipoveiculo = tipoveiculo;
        this.horario = horario;
        this.destino = destino;
        this.ajuda = ajuda;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public String gettipoVeiculo() {
        return tipoveiculo;
    }

    public void settipoVeiculo(String tipoveiculo) {
        this.tipoveiculo = tipoveiculo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public boolean isAjuda() {
        return ajuda;
    }

    public void setAjuda(boolean ajuda) {
        this.ajuda = ajuda;
    }
}
