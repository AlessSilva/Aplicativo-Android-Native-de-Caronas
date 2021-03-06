package com.example.appcaronamobile.Model;

import android.graphics.Bitmap;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Veiculo implements Serializable {

    private Long id;
    private String modelo;
    private String tipo;
    private String placa;
    private String cor;
    private String imagem;

    public Veiculo(){}

    public Veiculo( String modelo, String tipo, String placa, String cor, String imagem) {
        this.modelo = modelo;
        this.tipo = tipo;
        this.placa = placa;
        this.cor = cor;
        this.imagem = imagem;
    }

    public Veiculo(Long id, String modelo, String tipo, String placa, String cor, String imagem) {
        this.id = id;
        this.modelo = modelo;
        this.tipo = tipo;
        this.placa = placa;
        this.cor = cor;
        this.imagem = imagem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getImagem() {
        return imagem;
    }

    public String toString2(){ return this.modelo + " " + this.placa; }

    @Override
    public String toString() {
        String modeloV = "Modelo: " + modelo;
        String tipoV = "Tipo: " + tipo;
        String corV = "Cor: " + cor;
        String placaV = "Placa: " + placa;

        return modeloV + "\n" + tipoV + "\n" + corV + "\n" + placaV;
    }
}
