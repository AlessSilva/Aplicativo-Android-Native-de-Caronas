package com.example.appcaronamobile;

import android.content.Intent;
import android.os.Bundle;

import com.example.appcaronamobile.Dao.CaronaDAO;
import com.example.appcaronamobile.Dao.UsuarioDAO;
import com.example.appcaronamobile.Firebase.CaronaFirebase;
import com.example.appcaronamobile.Firebase.UsuarioFirebase;
import com.example.appcaronamobile.Model.Carona;
import com.example.appcaronamobile.Model.Usuario;
import com.example.appcaronamobile.Model.Veiculo;
import com.example.appcaronamobile.Util.Codes.RequestCodes;
import com.example.appcaronamobile.Util.Codes.ResultCodes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.appcaronamobile.ui.main.SectionsPagerAdapter;

public class HistoricoCaronasActivity extends AppCompatActivity {

    Usuario usuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_historico_caronas);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home_only, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.HomeReferenceOnly:
                Intent intent = new Intent(this, TelaPrincipalActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( resultCode == ResultCodes.EDIT_CARPÒOL_SUCESS && RequestCodes.EDIT_CARPOOL == requestCode ){

            int vagas = Integer.valueOf(data.getExtras().get("vagas").toString());
            String horario = data.getExtras().get("horario").toString();
            String dataa = data.getExtras().get("data").toString();
            String destino = data.getExtras().get("destino").toString();
            boolean ajuda = Boolean.valueOf(data.getExtras().get("ajuda").toString());
            double lat = Double.valueOf(data.getExtras().get("lat").toString());
            double lon = Double.valueOf(data.getExtras().get("long").toString());

            String id = data.getExtras().get("id").toString();

            Veiculo v = (Veiculo)  data.getSerializableExtra("veiculo");

            Carona carona = new Carona( id, usuario.getId() , vagas, v , horario, dataa, destino, ajuda, lat, lon );

            CaronaDAO caronaDAO = CaronaFirebase.getInstance();//CaronaDBMemory.getInstance();
            UsuarioDAO usuarioDAO = UsuarioFirebase.getInstance();//UsuarioDBMemory.getInstance();

            caronaDAO.editCarona(carona);
            if(usuario.removeCarona( carona )){
                Toast.makeText(this, "Deu certo", Toast.LENGTH_SHORT).show();
            }
            usuario.addCarona( carona );
            usuario = usuarioDAO.editUsuario(usuario);
            finish();

        }
    }
}