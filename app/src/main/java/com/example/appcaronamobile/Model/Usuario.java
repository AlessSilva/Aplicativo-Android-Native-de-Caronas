package com.example.appcaronamobile.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Usuario implements Serializable {
    private Long id;
    private String primeiroNome;
    private String sobrenome;
    private String telefone;
    private String email;
    private String senha;
    private String situacao;
    private String instituicao;
    private List<Veiculo> veiculos;
    private List<Carona> minhas_caronas;

    public Usuario(String primeiroNome, String sobrenome, String telefone,
                   String email, String senha, String situacao, String instituicao) {
        this.primeiroNome = primeiroNome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.situacao = situacao;
        this.instituicao = instituicao;
        this.veiculos = new ArrayList<Veiculo>();
        this.minhas_caronas = new ArrayList<Carona>();
    }

    public Usuario(Long id, String primeiroNome, String sobrenome, String telefone,
                   String email, String senha, String situacao, String instituicao) {
        this.id = id;
        this.primeiroNome = primeiroNome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.situacao = situacao;
        this.instituicao = instituicao;
        this.veiculos = new ArrayList<Veiculo>();
        this.minhas_caronas = new ArrayList<Carona>();
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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

    public void addVeiculo(Veiculo veiculo) {
        this.veiculos.add(veiculo);
    }

    public List<Carona> getMinhas_caronas() { return minhas_caronas; }

    public void setMinhas_caronas(List<Carona> minhas_caronas) { this.minhas_caronas = minhas_caronas; }

    public void addCarona( Carona carona ){ this.minhas_caronas.add(carona); }

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
