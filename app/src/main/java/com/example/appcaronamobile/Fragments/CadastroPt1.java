package com.example.appcaronamobile.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcaronamobile.R;
import com.example.appcaronamobile.Repository.MyListener;
import com.example.appcaronamobile.Util.Masks.MaskEditUtil;

public class CadastroPt1 extends Fragment {

    private View view = null;

    private EditText primeiroNome = null;
    private EditText sobrenome = null;
    private EditText telefone = null;
    private EditText email = null;
    private EditText senha = null;
    private EditText senhaRepetida = null;

    private Button proximo = null;
    private Button voltar = null;

    public CadastroPt1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.activity_cadastro_pt1, container, false);

        primeiroNome = view.findViewById(R.id.editTextPrimeiroNome);
        sobrenome = view.findViewById(R.id.editTextSobrenome);
        telefone = view.findViewById(R.id.editTextTelefone);

        telefone.addTextChangedListener(MaskEditUtil.mask(telefone, MaskEditUtil.FORMAT_PHONE));

        email = view.findViewById(R.id.editTextEmail);
        senha = view.findViewById(R.id.editTextSenha);
        senhaRepetida = view.findViewById(R.id.editTextSenhaRepetida);

        proximo =view.findViewById(R.id.buttonProximoCarona);
        voltar = view.findViewById(R.id.buttonVoltarCarona);

        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pn = primeiroNome.getText().toString();
                String sb = sobrenome.getText().toString();
                String tel = telefone.getText().toString();
                String eml = email.getText().toString();
                String s1 = senha.getText().toString();
                String s2 = senhaRepetida.getText().toString();

                if (pn.equals("") || sb.equals("") || tel.equals("") ||
                        eml.equals("") || s1.equals("") || s2.equals("")) {
                    Toast.makeText(getContext(), "Preencha todos os campos", Toast.LENGTH_LONG).show();
                } else if (!s1.equals(s2)) {
                    Toast.makeText(getContext(), "Senhas estão diferentes!", Toast.LENGTH_SHORT).show();
                } else {
                    sendData(pn, sb, tel, eml, s1);
                }

            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        return view;
    }

    private void sendData( String pn, String sn, String tel, String eml, String s1 ){

        MyListener myListener = (MyListener)getActivity();
        myListener.proximoFragmentoP1(pn, sn, tel, eml, s1);
    }

    private void back(){
        MyListener myListener = (MyListener)getActivity();
        myListener.voltarFragmentoP1();
    }

}
