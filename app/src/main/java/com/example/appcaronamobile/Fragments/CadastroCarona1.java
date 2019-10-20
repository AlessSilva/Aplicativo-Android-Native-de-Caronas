package com.example.appcaronamobile.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.appcaronamobile.R;


public class CadastCarona1 extends Fragment {


    View view = null;
    Spinner spinner_veiculos = null;
    ArrayAdapter<CharSequence> adapter_spinner = null;
    Button button = null;

    public CadastCarona1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_cadast_carona1, container, false);
        //button = (Button) view.findViewById(R.id.next);
        //button.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        ///        sendData();
        //    }
        //});
        spinner_veiculos = view.findViewById(R.id.spinnerVeiculos);
        adapter_spinner = ArrayAdapter.createFromResource(this.getContext(),R.array.Veiculos,android.R.layout.simple_spinner_item);
        spinner_veiculos.setAdapter(adapter_spinner);

        return view;
    }

    private void sendData(){
        MyListener myListener = (MyListener) getActivity();
        myListener.nextFragment(1);
    }

}
