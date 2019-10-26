package com.example.appcaronamobile.Dao;

import com.example.appcaronamobile.Model.Veiculo;

import java.util.ArrayList;

public interface VeiculoDAO {

    public void addVeiculo( Veiculo veiculo);

    public void editVeiculo( Veiculo veiculo );

    public void deleteVeiculo( Long veiculoId );

    public Veiculo getCarona( Long veiculoId );

    public ArrayList<Veiculo> getListaVeiculo();

}
