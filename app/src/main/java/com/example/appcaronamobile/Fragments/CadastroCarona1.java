package com.example.appcaronamobile.Fragments;


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

import com.example.appcaronamobile.R;
import com.example.appcaronamobile.Repository.MyListener2;
import com.example.appcaronamobile.Util.Masks.MaskEditUtil;


public class CadastroCarona1 extends Fragment {


    View view = null;

    EditText vagas = null;
    EditText data = null;
    EditText horario = null;
    EditText destino = null;
    RadioGroup ajudaG = null;
    RadioButton radioButton_selec;
    boolean ajuda = false;

    Spinner spinner_veiculos = null;
    ArrayAdapter<CharSequence> adapter_spinner = null;

    Button proximo = null;
    Button voltar = null;

    public CadastroCarona1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_cadastro_carona1, container, false);

        spinner_veiculos = view.findViewById(R.id.spinnerVeiculosCaronaCad);
        adapter_spinner = ArrayAdapter.createFromResource(this.getContext(),R.array.Veiculos,android.R.layout.simple_spinner_item);
        spinner_veiculos.setAdapter(adapter_spinner);

        vagas = view.findViewById(R.id.editTextVagasCaronaCad);
        horario = view.findViewById(R.id.editTextHorarioCaronaCad);
        horario.addTextChangedListener(MaskEditUtil.mask(horario, MaskEditUtil.FORMAT_HORARIO));
        data = view.findViewById(R.id.editTextDataCaronaCad);
        data.addTextChangedListener(MaskEditUtil.mask(data, MaskEditUtil.FORMAT_DATA));
        destino = view.findViewById(R.id.editTextDestinoCaronaCad);
        ajudaG = view.findViewById( R.id.radioGroupAjudaCaronaCad );

        radioButton_selec = view.findViewById( R.id.radioAjudaCaronaCad );

        proximo = view.findViewById(R.id.buttonProximoCarona);
        proximo.setOnClickListener(new View.OnClickListener() {
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

                if ( va == null || va.equals("") || ho.equals("") || da.equals("") || de.equals("") || ve.equals("") ) {
                    Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_LONG).show();
                }else {
                    sendData(Integer.parseInt(va),ve,ho,da,de,ajuda);
                }

            }
        });

        voltar = view.findViewById(R.id.buttonVoltarCarona);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        return view;
    }

    private void sendData( int vagas, String veiculo, String horario, String data, String destino, boolean ajuda ){

        MyListener2 myListener2 = (MyListener2) getActivity();
        myListener2.proximoFragmentoP1(vagas, veiculo, horario, data, destino, ajuda);

    }

    private void back(){

        MyListener2 myListener2 = (MyListener2) getActivity();
        myListener2.voltarFragmentoP1();

    }

}
