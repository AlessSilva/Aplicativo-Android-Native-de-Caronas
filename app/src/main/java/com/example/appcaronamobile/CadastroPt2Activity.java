package com.example.appcaronamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

import com.example.appcaronamobile.Util.CustomAdapters.Instituicoes_Adapter;
import com.example.appcaronamobile.Util.CustomAdapters.Situacoes_Adapter;

public class CadastroPt2Activity extends AppCompatActivity {
    private Spinner spinnerInstituicoes;
    private Spinner spinnerSituacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pt2);

        this.showInstituicoesSpinner();
        this.showSituacoesSpinner();
    }

    protected void showInstituicoesSpinner() {
        spinnerInstituicoes = findViewById(R.id.spinnerInstituicao);
        Instituicoes_Adapter adapter_instituicoes = new Instituicoes_Adapter(this);
        spinnerInstituicoes.setAdapter(adapter_instituicoes);
    }

    protected void showSituacoesSpinner() {
        spinnerSituacoes = findViewById(R.id.spinnerSituacao);
        Situacoes_Adapter adaptar_situacoes = new Situacoes_Adapter(this);
        spinnerSituacoes.setAdapter(adaptar_situacoes);
    }

    public void onClickFinalizar(View view) {
        Intent intent = new Intent(this, TelaPrincipalActivity.class);
        startActivity(intent);
    }

    public void onClickVoltar(View view) {
        finish();
    }
}