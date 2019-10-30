package com.example.appcaronamobile.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appcaronamobile.R;
import com.example.appcaronamobile.Repository.MyListener2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class CadastroCarona2 extends Fragment {

    View view = null;
    FragmentManager fragmentManager = null;

    public CadastroCarona2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_cadastro_carona2, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.mapLocalEncontro, new Map2(), "CadastroCaronaMap");
        transaction.commitAllowingStateLoss();

        FloatingActionButton fabOk = view.findViewById(R.id.fabOk);
        fabOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {ok();}
        });

        FloatingActionButton fabCancel = view.findViewById(R.id.fabCancel);
        fabCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {cancel();}
        });

        return view;
    }


    private void cancel(){

        getActivity().finish();
        //MyListener2 myListener2 = (MyListener2) getActivity();
        //myListener2.voltarFragmentoP2();

    }

    private void ok(){

        MyListener2 myListener2 = (MyListener2) getActivity();
        myListener2.finalizarFragmentoP2();

    }

}
