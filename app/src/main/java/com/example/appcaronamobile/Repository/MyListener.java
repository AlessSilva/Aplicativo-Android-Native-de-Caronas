package com.example.appcaronamobile.Repository;

import android.graphics.Bitmap;

public interface MyListener {

    void proximoFragmentoP1( String pn, String sn, String tel, String eml, String s1 );

    void voltarFragmentoP1();

    void finalizarFragmentoP2(String inst, String sit, byte[] imagem);

    void voltarFragmentoP2();
}