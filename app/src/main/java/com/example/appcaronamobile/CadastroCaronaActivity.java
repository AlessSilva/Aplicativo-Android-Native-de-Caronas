package com.example.appcaronamobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appcaronamobile.DBMemory.CaronaDBMemory;
import com.example.appcaronamobile.DBMemory.UsuarioDBMemory;
import com.example.appcaronamobile.Dao.CaronaDAO;
import com.example.appcaronamobile.Dao.UsuarioDAO;
import com.example.appcaronamobile.Fragments.CadastroCarona1;
import com.example.appcaronamobile.Fragments.CadastroCarona2;
import com.example.appcaronamobile.Model.Usuario;
import com.example.appcaronamobile.Repository.MyListener2;
import com.google.android.gms.maps.model.LatLng;

public class CadastroCaronaActivity extends AppCompatActivity implements MyListener2 {

    FragmentManager fragmentManager = null;

    private Usuario usuario = null;
    private int vagas;
    private String veiculo;
    private String horario;
    private String destino;
    private boolean ajuda;
    private LatLng latLng = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_carona);

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frame, new CadastroCarona1(), "CadastroCaronaPart1");
        transaction.commitAllowingStateLoss();

    }

    public void proximoFragmentoP1( int vagas, String veiculo, String horario, String destino, boolean ajuda ){

        this.vagas = vagas;
        this.veiculo = veiculo;
        this.horario = horario;
        this.destino = destino;
        this.ajuda = ajuda;

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame, new CadastroCarona2(), "CadastroCaronaPart2");
        transaction.commitAllowingStateLoss();

    }

    public void voltarFragmentoP1(){

        Toast.makeText(this, "Carona Cancelada", Toast.LENGTH_SHORT).show();
        finish();

    }

    public void finalizarFragmentoP2(){

        Intent intent = new Intent();
        intent.putExtra("vagas",vagas);
        intent.putExtra("horario",horario);
        intent.putExtra("destino",destino);
        intent.putExtra("ajuda",ajuda+"");
        intent.putExtra("lat",latLng.latitude+"");
        intent.putExtra("long",latLng.longitude+"");

        setResult(666666,intent);

        finish();

    }

    public void voltarFragmentoP2(){

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frame, new CadastroCarona1(), "CadastroCaronaPart1");
        transaction.commitAllowingStateLoss();

    }

    public void receberLatLng(LatLng latLn) {
        latLng = latLn;
    }
}