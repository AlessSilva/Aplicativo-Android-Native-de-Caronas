package com.example.appcaronamobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.example.appcaronamobile.Fragments.CadastCarona1;
import com.example.appcaronamobile.Fragments.CadastCarona2;
import com.example.appcaronamobile.Fragments.Map;

public class CadastCaronaActivity extends AppCompatActivity implements MyListener {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadast_carona);

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.frame, new Map(), "CadastroCaronaPart1");
        //transaction.add(R.id.frame, new CadastCarona1(), "CadastroCaronaPart1");
        transaction.commitAllowingStateLoss();

    }

    @Override
    public void nextFragment(int i) {
        if(i==1){
            Toast.makeText(this, "Deu certo", Toast.LENGTH_SHORT).show();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame, new CadastCarona2(), "CadastroCaronaPart2");
            transaction.commitAllowingStateLoss();
        }
    }
}
