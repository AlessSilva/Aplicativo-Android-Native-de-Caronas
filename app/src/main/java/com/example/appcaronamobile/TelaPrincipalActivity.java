package com.example.appcaronamobile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.appcaronamobile.DBMemory.CaronaDBMemory;
import com.example.appcaronamobile.DBMemory.UsuarioDBMemory;
import com.example.appcaronamobile.Dao.CaronaDAO;
import com.example.appcaronamobile.Dao.UsuarioDAO;
import com.example.appcaronamobile.Fragments.Conta;
import com.example.appcaronamobile.Fragments.ListarCaronas;
import com.example.appcaronamobile.Fragments.Map;
import com.example.appcaronamobile.Model.Carona;
import com.example.appcaronamobile.Model.Usuario;
import com.example.appcaronamobile.Model.Veiculo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TelaPrincipalActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private final int rqCadastroCarona = 300;
    private BottomNavigationView navigationView = null;
    private FloatingActionButton fab = null;

    FragmentManager fragmentManager = null;

    Usuario usuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        fragmentManager = getSupportFragmentManager();

        usuario = (Usuario)  getIntent().getSerializableExtra("usuario");

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.containerPrincipal,new Map(),"MapContainer");
        transaction.commitAllowingStateLoss();

        navigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        navigationView.setOnNavigationItemSelectedListener(this);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "Adicionar Carona", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), CadastroCaronaActivity.class);
                intent.putExtra("usuario", usuario);
                startActivityForResult(intent,rqCadastroCarona);

            }
        });


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
            case R.id.VerVeiculos:

                Toast.makeText(this, "Seus Veículos", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, MeusVeiculosActivity.class);

                intent.putExtra("usuario", usuario);

                startActivity(intent);

                break;
            case R.id.HistoricoCaronas:
                Toast.makeText(this, "Histórico de Caronas", Toast.LENGTH_SHORT).show();

                Intent intent2 = new Intent(this, HistoricoCaronasActivity.class);

                intent2.putExtra("usuario",usuario);

                startActivity(intent2);

                break;
            case R.id.VerAmigos:
                Toast.makeText(this, "Ver Amigos", Toast.LENGTH_SHORT).show();
                break;
            case R.id.AbrirChat:
                Toast.makeText(this, "Abrir Chat", Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_conta: {
                getSupportActionBar().setTitle("Conta");
                Conta conta = new Conta();
                Bundle arguments = new Bundle();
                arguments.putSerializable("usuario", usuario);
                conta.setArguments(arguments);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.containerPrincipal, conta,"ContaContainer");
                transaction.commitAllowingStateLoss();
                break;
            }
            case R.id.navigation_mapa: {
                getSupportActionBar().setTitle("Mapa");
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.containerPrincipal,new Map(),"MapContainer");
                transaction.commitAllowingStateLoss();
                break;
            }
            case R.id.navigation_caronas: {
                getSupportActionBar().setTitle("Caronas");
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.containerPrincipal,new ListarCaronas(),"CaronasContainer");
                transaction.commitAllowingStateLoss();
                break;
            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == rqCadastroCarona && resultCode == 666666){

            int vagas = Integer.valueOf(data.getExtras().get("vagas").toString());
            String horario = data.getExtras().get("horario").toString();
            String dataa = data.getExtras().get("data").toString();
            String destino = data.getExtras().get("destino").toString();
            boolean ajuda = Boolean.valueOf(data.getExtras().get("ajuda").toString());
            double lat = Double.valueOf(data.getExtras().get("lat").toString());
            double lon = Double.valueOf(data.getExtras().get("long").toString());

            Veiculo v = new Veiculo( "Fusquinha anos 50", "CARRO", "placa", "amarelo" );
            Carona carona = new Carona( usuario.getId() , vagas, v , horario, dataa, destino, ajuda, lat, lon );

            CaronaDAO caronaDAO = CaronaDBMemory.getInstance();
            UsuarioDAO usuarioDAO = UsuarioDBMemory.getInstance();

            usuario.addCarona( caronaDAO.addCarona(carona) );
            usuario = usuarioDAO.editUsuario(usuario);

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}