package com.example.appcaronamobile.Dao;

import com.example.appcaronamobile.Model.Carona;
import com.example.appcaronamobile.Model.Usuario;

import java.util.ArrayList;

public interface CaronaDAO {

    public Carona addCarona( Carona carona);

    public Carona editCarona( Carona carona );

    public void deleteCarona( String caronaId );

    public Carona getCarona( String caronaId );

    public ArrayList<Carona> getListaCarona();

    public ArrayList<Carona> getListaCarona(String usuario);

    public ArrayList<Carona> getListaCaronaParticipacao(String usuario);

}
