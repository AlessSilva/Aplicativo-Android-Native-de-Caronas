package com.example.appcaronamobile.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appcaronamobile.DBMemory.CaronaDBMemory;
import com.example.appcaronamobile.Dao.CaronaDAO;
import com.example.appcaronamobile.Model.Carona;
import com.example.appcaronamobile.R;
import com.example.appcaronamobile.Repository.MyAdapterListarCaronas;

import java.util.ArrayList;
import java.util.List;

public class ListarCaronas extends Fragment {

    View view = null;
    List<Carona> caronas;

    CaronaDAO caronaDAO = null;

    public ListarCaronas() {
    }

    RecyclerView mRecycleView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_listar_caronas, container, false);

        caronaDAO = CaronaDBMemory.getInstance();

        mRecycleView = view.findViewById(R.id.recyclerCaronas);
        GridLayoutManager gridLayoutManager = new GridLayoutManager( this.getContext(),1 );
        mRecycleView.setLayoutManager(gridLayoutManager);

        caronas = caronaDAO.getListaCarona();
        MyAdapterListarCaronas adapterListCaronas = new MyAdapterListarCaronas(this.getContext(),caronas);

        mRecycleView.setAdapter(adapterListCaronas);

        return view;
    }

}
