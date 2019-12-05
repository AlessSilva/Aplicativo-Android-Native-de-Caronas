package com.example.appcaronamobile.Model;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import android.net.Uri;

@SuppressWarnings("serial")
public class Usuario implements Serializable {
    private String id;
    private String primeiroNome;
    private String sobrenome;
    private String telefone;
    private String email;
    private String senha;
    private String situacao;
    private String instituicao;
    private String imagem;
    private List<Veiculo> veiculos;
    private List<Carona> minhas_caronas;

    public Usuario(){
        this.veiculos = new ArrayList<Veiculo>();
        this.minhas_caronas = new ArrayList<Carona>();
    }

    public Usuario(String primeiroNome, String sobrenome, String telefone,
                   String email, String senha, String situacao, String instituicao, String imagem) {
        this.primeiroNome = primeiroNome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.situacao = situacao;
        this.instituicao = instituicao;
        this.imagem = imagem;
        this.veiculos = new ArrayList<Veiculo>();
        this.minhas_caronas = new ArrayList<Carona>();
    }

    public Usuario(String id, String primeiroNome, String sobrenome, String telefone,
                   String email, String senha, String situacao, String instituicao, String imagem) {
        this.id = id;
        this.primeiroNome = primeiroNome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.situacao = situacao;
        this.instituicao = instituicao;
        this.imagem = imagem;
        this.veiculos = new ArrayList<Veiculo>();
        this.minhas_caronas = new ArrayList<Carona>();
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getImagem() {
        return imagem;
    }

    public Veiculo getVeiculoAt(int position) {
        return veiculos.get(position);
    }

    public void overrideVeiculo(int position, Veiculo veiculo) {
        veiculos.set(position, veiculo);
    }

    public void addVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }

    public List<Carona> getMinhas_caronas() { return minhas_caronas; }

    public void setMinhas_caronas(List<Carona> minhas_caronas) { this.minhas_caronas = minhas_caronas; }

    public void addCarona( Carona carona ){ this.minhas_caronas.add(carona); }

    public boolean removeCarona( Carona carona ){

        int indice = -1;

        for(int i = 0; i < minhas_caronas.size(); i++ ){
            if( minhas_caronas.get(i).getId().equals(carona.getId()) ){
                indice = i;
                break;
            }
        }
        if( indice != -1 ){
            this.minhas_caronas.remove(indice);
            return true;
        }

        return false;

    }

    @Override
    public String toString() {
        return id + "," +
                primeiroNome + "," +
                sobrenome + "," +
                telefone + "," +
                email + "," +
                senha + "," +
                situacao + "," +
                instituicao;
    }
}
