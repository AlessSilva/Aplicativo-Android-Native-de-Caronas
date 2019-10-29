package com.example.appcaronamobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appcaronamobile.Fragments.CadastroCarona1;
import com.example.appcaronamobile.Fragments.CadastroPt1;
import com.example.appcaronamobile.Fragments.CadastroPt2;
import com.example.appcaronamobile.Repository.MyListener;

public class CadastroUsuarioActivity extends AppCompatActivity implements MyListener {

    FragmentManager fragmentManager = null;

    String primNome;
    String segNome;
    String telefone;
    String email;
    String senha;
    String instituicao;
    String situacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frameCadastro, new CadastroPt1(), "CadastroUsuarioPart1");
        transaction.commitAllowingStateLoss();

    }

    @Override
    public void proximoFragmentoP1(String pn, String sn, String tel, String eml, String s1) {

        primNome = pn;
        segNome = sn;
        telefone = tel;
        email = eml;
        senha = s1;

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameCadastro, new CadastroPt2(), "CadastroUsuarioPart2");
        transaction.commitAllowingStateLoss();

    }

    @Override
    public void voltarFragmentoP1() {

        finish();
        setResult( 113 );

    }

    @Override
    public void finalizarFragmentoP2(String inst, String sit) {

        instituicao = inst;
        situacao = sit;

        Toast.makeText(this, inst+" "+" "+" "+sit, Toast.LENGTH_SHORT).show();

        Intent intent = new Intent();

        intent.putExtra("primeiro_nome", primNome);
        intent.putExtra("sobrenome", segNome);
        intent.putExtra("telefone", telefone);
        intent.putExtra("email", email);
        intent.putExtra("senha", senha);
        intent.putExtra("situacao", situacao);
        intent.putExtra("instituicao", instituicao);

        setResult(112, intent);
        finish();

    }

    @Override
    public void voltarFragmentoP2() {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameCadastro, new CadastroPt1(), "CadastroUsuarioPart2");
        transaction.commitAllowingStateLoss();

    }

}
