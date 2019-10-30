package com.example.appcaronamobile.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carona implements Serializable {

    private Long id;
    private int vagas;
    private Veiculo veiculo;
    private String horario;
    private String data;
    private String destino;
    private double latLocalEncontro;
    private double lngLocalEncontro;
    private boolean ajuda;
    private Long id_responsavel;
    List<Participante> participantes;

    public Carona(Long id, Long id_responsavel ,int vagas, Veiculo veiculo, String horario, String data , String destino, boolean ajuda, double latitude, double longitude) {
        this.id = id;
        this.id_responsavel = id_responsavel;
        this.vagas = vagas;
        this.veiculo = veiculo;
        this.horario = horario;
        this.data = data;
        this.destino = destino;
        this.ajuda = ajuda;
        this.latLocalEncontro = latitude;
        this.lngLocalEncontro = longitude;
        this.participantes = new ArrayList<Participante>();
    }

    public Carona(Long id_responsavel, int vagas, Veiculo veiculo, String horario, String data ,String destino, boolean ajuda, double latitude, double longitude) {
        this.id_responsavel = id_responsavel;
        this.vagas = vagas;
        this.veiculo = veiculo;
        this.horario = horario;
        this.data = data;
        this.destino = destino;
        this.ajuda = ajuda;
        this.latLocalEncontro = latitude;
        this.lngLocalEncontro = longitude;
        this.participantes = new ArrayList<Participante>();
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

    public void setLatLocalEncontro(double latLocalEncontro) { this.latLocalEncontro = latLocalEncontro; }

    public double getLngLocalEncontro() {
        return lngLocalEncontro;
    }

    public void setLngLocalEncontro(double lngLocalEncontro) { this.lngLocalEncontro = lngLocalEncontro; }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Participante> participantes) { this.participantes = participantes; }

    public Long getId_responsavel() {
        return id_responsavel;
    }

    public void setId_responsavel(Long id_responsavel) {
        this.id_responsavel = id_responsavel;
    }

    public void addUsuario( Participante usuario ){ this.participantes.add(usuario); }

    public int getLikes() {
        int likes = 0;
        for( Participante p : participantes ){
            if (p.isLike()){
                likes++;
            }
        }
        return likes;
    }

    public int getDislikes() {
        int dislikes = 0;
        for( Participante p : participantes ){
            if (p.isDislike()){
                dislikes++;
            }
        }
        return dislikes;
    }

    public Participante getParticipante( Usuario usuario ){

        for ( Participante p : participantes  ){

            if ( p.getUsuario().getId().equals( usuario.getId() ) ){
                return p;
            }

        }

        return null;

    }

    public ArrayList<Participante> getConfirmados(){

        ArrayList<Participante> confirmados = new ArrayList<Participante>();

        for( Participante p : participantes ){
            if ( p.isConfirmacao() ){
                confirmados.add(p);
            }
        }

        return confirmados;

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
