package com.example.appcaronamobile.Dao;

import com.example.appcaronamobile.Model.Carona;

import java.util.ArrayList;

public interface CaronaDAO {

    public void addCarona( Carona carona);

    public void editCarona( Carona carona );

    public void deleteCarona( Long caronaId );

    public Carona getCarona( Long caronaId );

    public ArrayList<Carona> getListaCarona();

}
