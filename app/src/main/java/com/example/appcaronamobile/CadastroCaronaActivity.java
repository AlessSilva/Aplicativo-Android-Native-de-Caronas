package com.example.appcaronamobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.example.appcaronamobile.Fragments.CadastroCarona1;
import com.example.appcaronamobile.Fragments.CadastroCarona2;
import com.example.appcaronamobile.Repository.MyListener;

public class CadastroCaronaActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_carona);

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.frame, new CadastroCarona1(), "CadastroCaronaPart1");
        transaction.commitAllowingStateLoss();

    }

}