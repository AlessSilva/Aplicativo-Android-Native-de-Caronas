package com.example.appcaronamobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final int rqCadastCaronaP1 = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.AddCarona:
                Toast.makeText(this, "Adicionar Carona", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(this,CadastCaronaActivity.class);
                startActivityForResult(i, rqCadastCaronaP1);

                break;
            case R.id.HistoricoCaronas:
                Toast.makeText(this, "Históricos de Caronas", Toast.LENGTH_SHORT).show();
                break;
            case R.id.VerAmigos:
                Toast.makeText(this, "Ver Amigos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.VerVeiculos:
                Toast.makeText(this, "Ver Veículos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.AbrirChart:
                Toast.makeText(this, "Abrir Chart", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Sobre:
                Toast.makeText(this, "Sobre", Toast.LENGTH_SHORT).show();
                break;
            case R.id.Configuracoes:
                Toast.makeText(this, "Configurações", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
