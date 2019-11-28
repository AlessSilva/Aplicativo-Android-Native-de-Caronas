package com.example.appcaronamobile.Model;

import java.io.Serializable;

public class Participante implements Serializable {

    private String id;
    private String nome;
    private String telefone;
    private String email;
    private boolean like;
    private boolean dislike;
    private String localEncontro;
    private boolean confirmacao;

    public Participante(){
    }

    public Participante(Usuario usuario,String localEncontro) {
        this.id = usuario.getId();
        this.nome = usuario.getPrimeiroNome();
        this.telefone = usuario.getTelefone();
        this.email = usuario.getEmail();
        this.like = false;
        this.dislike = false;
        this.localEncontro = localEncontro;
        this.confirmacao = false;
    }

    public Participante(Usuario usuario, boolean like, String localEncontro, boolean confirmacao) {
        this.id = id;
        this.nome = usuario.getPrimeiroNome();
        this.telefone = usuario.getTelefone();
        this.email = usuario.getEmail();
        this.like = like;
        this.localEncontro = localEncontro;
        this.confirmacao = confirmacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public String getLocalEncontro() {
        return localEncontro;
    }

    public void setLocalEncontro(String localEncontro) {
        this.localEncontro = localEncontro;
    }

    public boolean isConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(boolean confirmacao) {
        this.confirmacao = confirmacao;
    }

    public boolean isDislike() {
        return dislike;
    }

    public void setDislike(boolean dislike) {
        this.dislike = dislike;
    }
}
