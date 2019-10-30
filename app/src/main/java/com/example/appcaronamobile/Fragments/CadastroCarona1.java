package com.example.appcaronamobile.Fragments;


import android.app.Service;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appcaronamobile.Model.Usuario;
import com.example.appcaronamobile.Model.Veiculo;
import com.example.appcaronamobile.R;
import com.example.appcaronamobile.Repository.MyListener2;
import com.example.appcaronamobile.Util.Masks.MaskEditUtil;

import java.util.ArrayList;


public class CadastroCarona1 extends Fragment {

    View view = null;

    EditText vagas = null;
    EditText data = null;
    EditText horario = null;
    EditText destino = null;
    RadioButton radioButton_selec;
    Usuario usuario = null;

    boolean ajuda = false;

    Spinner spinner_veiculos = null;
    ArrayAdapter<String> adapter_spinner = null;

    Button buttonProximo = null;
    Button buttonVoltar = null;

    public CadastroCarona1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_cadastro_carona1, container, false);

        Bundle arguments = getArguments();
        usuario = (Usuario) arguments.getSerializable("usuario");

        ArrayList<String> veiculos = new ArrayList<String>();

        veiculos.add("Escolha um Veículo");
        for( Veiculo v : usuario.getVeiculos() ){

            veiculos.add(v.toString2());

        }

        adapter_spinner = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item,veiculos);

        spinner_veiculos = view.findViewById(R.id.spinnerVeiculosCaronaCad);
        spinner_veiculos.setAdapter(adapter_spinner);

        vagas = view.findViewById(R.id.editTextVagasCaronaCad);
        horario = view.findViewById(R.id.editTextHorarioCaronaCad);
        horario.addTextChangedListener(MaskEditUtil.mask(horario, MaskEditUtil.FORMAT_HORARIO));
        data = view.findViewById(R.id.editTextDataCaronaCad);
        data.addTextChangedListener(MaskEditUtil.mask(data, MaskEditUtil.FORMAT_DATA));
        destino = view.findViewById(R.id.editTextDestinoCaronaCad);

        radioButton_selec = view.findViewById( R.id.radioAjudaCaronaCad );

        buttonProximo = view.findViewById(R.id.buttonProximoCarona);
        buttonProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String va = vagas.getText().toString();
                String ho = horario.getText().toString();
                String da = data.getText().toString();
                String de = destino.getText().toString();
                String ve = spinner_veiculos.getSelectedItem().toString();

                if(radioButton_selec.isChecked()){
                    ajuda = true;
                }

                if ( va == null || va.equals("") || ho.equals("") || da.equals("") || de.equals("") || ve.equals("Escolha um Veículo") ) {
                    Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_LONG).show();
                }else {
                    sendData(Integer.parseInt(va),ve,ho,da,de,ajuda);
                }

            }
        });

        buttonVoltar = view.findViewById(R.id.buttonVoltarCarona);
        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        return view;
    }

    private void sendData( int vagas, String veiculo, String horario, String data, String destino, boolean ajuda ){

        Veiculo v = usuario.getVeiculoAt(spinner_veiculos.getSelectedItemPosition()-1 );
        MyListener2 myListener2 = (MyListener2) getActivity();
        myListener2.proximoFragmentoP1(vagas, v, horario, data, destino, ajuda);

    }

    private void back(){

        MyListener2 myListener2 = (MyListener2) getActivity();
        myListener2.voltarFragmentoP1();

    }

}
