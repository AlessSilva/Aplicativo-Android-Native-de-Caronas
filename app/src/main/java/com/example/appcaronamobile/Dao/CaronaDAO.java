package com.example.appcaronamobile.Dao;

import com.example.appcaronamobile.Model.Carona;

import java.util.ArrayList;

public interface CaronaDAO {

    public Carona addCarona( Carona carona);

    public Carona editCarona( Carona carona );

    public void deleteCarona( Long caronaId );

    public Carona getCarona( Long caronaId );

    public ArrayList<Carona> getListaCarona();

}
