package com.example.appcaronamobile.Model;

import java.util.ArrayList;
import java.util.List;

public class Carona {

    private Long id;
    private int vagas;
    private Veiculo veiculo; //TODO: Deve ser um objeto
    private String horario;
    private String destino;
    private double latLocalEncontro;
    private double lngLocalEncontro;
    private boolean ajuda;
    private Long id_responsavel;
    List<Usuario> participantes;

    public Carona(Long id, Long id_responsavel ,int vagas, Veiculo veiculo, String horario, String destino, boolean ajuda, double latitude, double longitude, List<Usuario> usuarios) {
        this.id = id;
        this.id_responsavel = id_responsavel;
        this.vagas = vagas;
        this.veiculo = veiculo;
        this.horario = horario;
        this.destino = destino;
        this.ajuda = ajuda;
        this.latLocalEncontro = latitude;
        this.lngLocalEncontro = longitude;
        this.participantes = usuarios;
    }

    public Carona(Long id_responsavel, int vagas, Veiculo veiculo, String horario, String destino, boolean ajuda, double latitude, double longitude, List<Usuario> usuarios) {
        this.id_responsavel = id_responsavel;
        this.vagas = vagas;
        this.veiculo = veiculo;
        this.horario = horario;
        this.destino = destino;
        this.ajuda = ajuda;
        this.latLocalEncontro = latitude;
        this.lngLocalEncontro = longitude;
        this.participantes = usuarios;
    }

    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatLocalEncontro() {
        return latLocalEncontro;
    }

    public void setLatLocalEncontro(double latLocalEncontro) {
        this.latLocalEncontro = latLocalEncontro;
    }

    public double getLngLocalEncontro() {
        return lngLocalEncontro;
    }

    public void setLngLocalEncontro(double lngLocalEncontro) {
        this.lngLocalEncontro = lngLocalEncontro;
    }

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Usuario> participantes) {
        this.participantes = participantes;
    }

    public Long getId_responsavel() {
        return id_responsavel;
    }

    public void setId_responsavel(Long id_responsavel) {
        this.id_responsavel = id_responsavel;
    }
}
