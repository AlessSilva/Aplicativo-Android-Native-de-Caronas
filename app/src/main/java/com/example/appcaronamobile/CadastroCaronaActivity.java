package com.example.appcaronamobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.example.appcaronamobile.Fragments.CadastroCarona1;
import com.example.appcaronamobile.Fragments.CadastroCarona2;
import com.example.appcaronamobile.Fragments.CadastroPt2;
import com.example.appcaronamobile.Fragments.Map;
import com.example.appcaronamobile.Fragments.Map2;
import com.example.appcaronamobile.Repository.MyListener;
import com.example.appcaronamobile.Repository.MyListener2;
import com.google.android.gms.maps.model.LatLng;

public class CadastroCaronaActivity extends AppCompatActivity implements MyListener2 {

    FragmentManager fragmentManager;

    private int vagas;
    private String veiculo;
    private String horario;
    private String destino;
    private boolean ajuda;
    private LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_carona);

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

        Toast.makeText(this, vagas+"\n"+veiculo+"\n"+" "+horario+"\n"+destino+"\n"+ajuda+"\n"+latLng.toString(), Toast.LENGTH_SHORT).show();
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