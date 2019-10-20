package com.example.appcaronamobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.appcaronamobile.Fragments.CadastCarona1;

public class CadastCaronaActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadast_carona);

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.frame, new CadastCarona1(), "CadastroCaronaPart1");
        transaction.commitAllowingStateLoss();

    }

}
