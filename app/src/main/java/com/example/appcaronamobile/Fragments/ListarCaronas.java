package com.example.appcaronamobile.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appcaronamobile.Model.Carona;
import com.example.appcaronamobile.R;
import com.example.appcaronamobile.Repository.MyAdapterListarCaronas;

import java.util.ArrayList;
import java.util.List;

public class ListarCaronas extends Fragment {

    View view = null;
    List<Carona> caronas;


    public ListarCaronas() {
    }

    RecyclerView mRecycleView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_listar_caronas, container, false);

        mRecycleView = view.findViewById(R.id.recyclerCaronas);
        GridLayoutManager gridLayoutManager = new GridLayoutManager( this.getContext(),1 );
        mRecycleView.setLayoutManager(gridLayoutManager);

        caronas = new ArrayList<Carona>();
        caronas.add( new Carona(2,"CARRO","15:00:00","Passo pelo sinal e paro perto do São Geraldo", false) );
        caronas.add( new Carona(1,"CARRO","16:30:00","Só vem", false) );
        caronas.add( new Carona(1,"MOTO","10:00:00","Pro IFCE direto", true) );
        caronas.add( new Carona(4,"CARRO","11:59:00","Lero Lero te levo até o cedro", false) );
        caronas.add( new Carona(1,"MOTO","07:50:00","Cedro", true) );

        MyAdapterListarCaronas adapterListCaronas = new MyAdapterListarCaronas(this.getContext(),caronas);

        mRecycleView.setAdapter(adapterListCaronas);

        return view;
    }

}
