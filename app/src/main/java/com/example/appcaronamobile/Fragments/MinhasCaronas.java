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
import com.example.appcaronamobile.Model.Usuario;
import com.example.appcaronamobile.R;
import com.example.appcaronamobile.Util.CustomAdapters.MyAdapterListarCaronas;
import com.example.appcaronamobile.Util.CustomAdapters.MyAdapterMinhasCaronas;

import java.util.List;

public class MinhasCaronas extends Fragment {

    Usuario usuario;
    View view;
    CaronaDAO caronaDAO = null;
    RecyclerView mRecycleView;
    List<Carona> caronas;

    public MinhasCaronas() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_minhas_caronas, container, false);

        usuario = (Usuario) getActivity().getIntent().getSerializableExtra("usuario");

        caronaDAO = CaronaDBMemory.getInstance();

        mRecycleView = view.findViewById(R.id.recyclerMCaronas);
        GridLayoutManager gridLayoutManager = new GridLayoutManager( this.getContext(),1 );
        mRecycleView.setLayoutManager(gridLayoutManager);

        caronas = caronaDAO.getListaCarora(usuario.getId());
        MyAdapterMinhasCaronas adapterListCaronas = new MyAdapterMinhasCaronas(this.getContext(),caronas);

        mRecycleView.setAdapter(adapterListCaronas);

        return view;
    }

}
