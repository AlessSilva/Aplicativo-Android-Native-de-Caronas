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
import com.example.appcaronamobile.Firebase.CaronaFirebase;
import com.example.appcaronamobile.Model.Carona;
import com.example.appcaronamobile.Model.Usuario;
import com.example.appcaronamobile.R;
import com.example.appcaronamobile.Util.CustomAdapters.MyAdapterCaronasParticipacao;
import com.example.appcaronamobile.Util.CustomAdapters.MyAdapterMinhasCaronas;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CaronasParticipacao extends Fragment {

    Usuario usuario;
    View view;
    CaronaDAO caronaDAO = null;
    RecyclerView mRecycleView;
    List<Carona> caronas;

    public CaronasParticipacao() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_caronas_participacao, container, false);

        usuario = (Usuario) getActivity().getIntent().getSerializableExtra("usuario");

        caronaDAO = CaronaFirebase.getInstance();//CaronaDBMemory.getInstance();

        mRecycleView = view.findViewById(R.id.recyclerPCaronas);
        GridLayoutManager gridLayoutManager = new GridLayoutManager( this.getContext(),1 );
        mRecycleView.setLayoutManager(gridLayoutManager);

        caronas = caronaDAO.getListaCaronaParticipacao(usuario.getId());

        MyAdapterCaronasParticipacao adapterListCaronas = new MyAdapterCaronasParticipacao(this.getContext(),caronas);

        mRecycleView.setAdapter(adapterListCaronas);

        return view;
    }

}
