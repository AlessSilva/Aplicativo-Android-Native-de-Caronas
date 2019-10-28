package com.example.appcaronamobile.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appcaronamobile.Model.Usuario;
import com.example.appcaronamobile.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CaronasParticipacao extends Fragment {

    Usuario usuario;

    public CaronasParticipacao() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        usuario = (Usuario) getActivity().getIntent().getSerializableExtra("usuario");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_caronas_participacao, container, false);
    }

}
