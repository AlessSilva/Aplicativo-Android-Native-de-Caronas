package com.example.appcaronamobile.Repository;

import com.google.android.gms.maps.model.LatLng;

public interface MyListener2 {

    void proximoFragmentoP1( int vagas, String veiculo, String horario, String destino, boolean ajuda );

    void voltarFragmentoP1();

    void finalizarFragmentoP2(LatLng latLng);

    void voltarFragmentoP2();

    void receberLatLng(LatLng latLng);

}
