package com.example.appcaronamobile.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appcaronamobile.DBMemory.CaronaDBMemory;
import com.example.appcaronamobile.Dao.CaronaDAO;
import com.example.appcaronamobile.Firebase.CaronaFirebase;
import com.example.appcaronamobile.Model.Carona;
import com.example.appcaronamobile.Model.Usuario;
import com.example.appcaronamobile.R;
import com.example.appcaronamobile.TelaPrincipalActivity;
import com.example.appcaronamobile.Util.CustomAdapters.MyAdapterListarCaronas;
import com.example.appcaronamobile.Util.CustomAdapters.MyAdapterMinhasCaronas;
import com.google.auto.value.AutoAnnotation;

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

        caronaDAO = CaronaFirebase.getInstance();//CaronaDBMemory.getInstance();

        mRecycleView = view.findViewById(R.id.recyclerMCaronas);
        GridLayoutManager gridLayoutManager = new GridLayoutManager( this.getContext(),1 );
        mRecycleView.setLayoutManager(gridLayoutManager);

        caronas = caronaDAO.getListaCarona(usuario.getId());
        Toast.makeText(view.getContext(), ""+caronas.size(), Toast.LENGTH_SHORT).show();
        MyAdapterMinhasCaronas adapterListCaronas = new MyAdapterMinhasCaronas(this.getActivity(),this.getContext(),caronas);

        mRecycleView.setAdapter(adapterListCaronas);

        return view;
    }
}
