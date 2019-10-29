package com.example.appcaronamobile.Repository;

import com.example.appcaronamobile.Model.Veiculo;
import com.google.android.gms.maps.model.LatLng;

public interface MyListener2 {

    void proximoFragmentoP1(int vagas, Veiculo veiculo, String horario, String data, String destino, boolean ajuda );

    void voltarFragmentoP1();

    void finalizarFragmentoP2();

    void voltarFragmentoP2();

    void receberLatLng(LatLng latLng);

}
