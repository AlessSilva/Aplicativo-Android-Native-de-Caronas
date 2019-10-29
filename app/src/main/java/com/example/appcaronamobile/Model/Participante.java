package com.example.appcaronamobile.Model;

import java.io.Serializable;

public class Participante implements Serializable {

    private Usuario usuario;
    private boolean like;
    private boolean dislike;
    private String localEncontro;
    private boolean confirmacao;

    public Participante(Usuario usuario,String localEncontro) {
        this.usuario = usuario;
        this.like = false;
        this.dislike = false;
        this.localEncontro = localEncontro;
        this.confirmacao = false;
    }

    public Participante(Usuario usuario, boolean like, String localEncontro, boolean confirmacao) {
        this.usuario = usuario;
        this.like = like;
        this.localEncontro = localEncontro;
        this.confirmacao = confirmacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
