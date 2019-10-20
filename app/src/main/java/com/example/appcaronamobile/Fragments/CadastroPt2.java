package com.example.appcaronamobile.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.appcaronamobile.R;
import com.example.appcaronamobile.Repository.MyListener;
import com.example.appcaronamobile.Util.CustomAdapters.Instituicoes_Adapter;
import com.example.appcaronamobile.Util.CustomAdapters.Situacoes_Adapter;

public class CadastroPt2 extends Fragment {

    View view = null;

    private Spinner spinnerInstituicoes=null;
    private Spinner spinnerSituacoes=null;

    private Button finalizar=null;
    private Button voltar=null;

    public CadastroPt2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.activity_cadastro_pt2, container, false);

        this.showInstituicoesSpinner();
        this.showSituacoesSpinner();

        finalizar = view.findViewById( R.id.buttonFinalizar );
        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Pegar valores do Spinner!!!
                sendData("UFC","Aluno");
            }
        });

        voltar = view.findViewById( R.id.buttonVoltarPt2 );
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        return view;
    }

    protected void showInstituicoesSpinner() {
        spinnerInstituicoes = view.findViewById(R.id.spinnerInstituicao);
        Instituicoes_Adapter adapter_instituicoes = new Instituicoes_Adapter(getContext());
        spinnerInstituicoes.setAdapter(adapter_instituicoes);
    }

    protected void showSituacoesSpinner() {
        spinnerSituacoes = view.findViewById(R.id.spinnerSituacao);
        Situacoes_Adapter adaptar_situacoes = new Situacoes_Adapter(getContext());
        spinnerSituacoes.setAdapter(adaptar_situacoes);
    }

    private void sendData( String inst, String sit ){

        MyListener myListener = (MyListener)getActivity();
        myListener.finalizarFragmentoP2(inst,sit);

    }

    private void back(){
        MyListener myListener = (MyListener)getActivity();
        myListener.voltarFragmentoP2();
    }

}
