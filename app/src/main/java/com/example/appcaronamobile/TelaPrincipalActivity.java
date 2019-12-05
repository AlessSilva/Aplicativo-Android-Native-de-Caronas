package com.example.appcaronamobile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.appcaronamobile.Dao.CaronaDAO;
import com.example.appcaronamobile.Dao.UsuarioDAO;
import com.example.appcaronamobile.Firebase.CaronaFirebase;
import com.example.appcaronamobile.Firebase.UsuarioFirebase;
import com.example.appcaronamobile.Fragments.ListarCaronas;
import com.example.appcaronamobile.Fragments.Map;
import com.example.appcaronamobile.Model.Carona;
import com.example.appcaronamobile.Model.Usuario;
import com.example.appcaronamobile.Model.Veiculo;
import com.example.appcaronamobile.Util.Codes.RequestCodes;
import com.example.appcaronamobile.Util.Codes.ResultCodes;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TelaPrincipalActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView = null;
    private FloatingActionButton fab = null;

    FragmentManager fragmentManager = null;

    Usuario usuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.askMapPermissions();
        this.statusCheck();

        setContentView(R.layout.activity_tela_principal);


        fragmentManager = getSupportFragmentManager();

        usuario = (Usuario)  getIntent().getSerializableExtra("usuario");

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.containerPrincipal,new Map(),"MapContainer");
        transaction.commitAllowingStateLoss();

        navigationView = (BottomNavigationView) findViewById(R.id.navigationView);
        navigationView.setOnNavigationItemSelectedListener(this);

        fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "Adicionar Carona", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(view.getContext(), CadastroCaronaActivity.class);
                intent.putExtra("usuario", usuario);
                startActivityForResult(intent, RequestCodes.CAD_CARPOOL);

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

        switch (item.getItemId()) {
            case R.id.HomeReference:
                // REFRESH PAGE
                finish();
                startActivity(getIntent());
                break;
            case R.id.VerVeiculos:

                Intent intent = new Intent(this, MeusVeiculosActivity.class);

                intent.putExtra("usuario", usuario);

                startActivityForResult(intent, RequestCodes.MEUS_VEICULOS);

                break;

            case R.id.Conta:
                Intent intent3 = new Intent(this, EditarContaActivity.class);
                intent3.putExtra("usuario", usuario);
                startActivityForResult(intent3, RequestCodes.EDITAR_CONTA);
                break;

            case R.id.HistoricoCaronas:

                Intent intent2 = new Intent(this, HistoricoCaronasActivity.class);

                intent2.putExtra("usuario",usuario);

                startActivity(intent2);

                break;
            case R.id.Sobre:
                Intent intent4 = new Intent(this, SobreActivity.class);
                startActivity(intent4);
                break;
            case R.id.Sair:

                finish();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.navigation_conta: {
//                getSupportActionBar().setTitle("Conta");
//                Conta conta = new Conta();
//                Bundle arguments = new Bundle();
//                arguments.putSerializable("usuario", usuario);
//                conta.setArguments(arguments);
//                FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.containerPrincipal, conta,"ContaContainer");
//                transaction.commitAllowingStateLoss();
//                break;
//            }
            case R.id.navigation_mapa: {
                getSupportActionBar().setTitle("Mapa");
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.containerPrincipal,new Map(),"MapContainer");
                transaction.commitAllowingStateLoss();
                break;
            }
            case R.id.navigation_caronas: {
                getSupportActionBar().setTitle("Caronas");
                ListarCaronas listarCaronas = new ListarCaronas();
                Bundle arguments = new Bundle();
                arguments.putSerializable("usuario",usuario);
                listarCaronas.setArguments(arguments);
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.containerPrincipal,listarCaronas,"CaronasContainer");
                transaction.commitAllowingStateLoss();
                break;
            }
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == RequestCodes.CAD_CARPOOL && resultCode == ResultCodes.CAD_CARPOOL_SUCESS){

            int vagas = Integer.valueOf(data.getExtras().get("vagas").toString());
            String horario = data.getExtras().get("horario").toString();
            String dataa = data.getExtras().get("data").toString();
            String destino = data.getExtras().get("destino").toString();
            boolean ajuda = Boolean.valueOf(data.getExtras().get("ajuda").toString());
            double lat = Double.valueOf(data.getExtras().get("lat").toString());
            double lon = Double.valueOf(data.getExtras().get("long").toString());

            Veiculo v = (Veiculo)  data.getSerializableExtra("veiculo");
            Toast.makeText(this, v.getTipo()+"", Toast.LENGTH_SHORT).show();
            Carona carona = new Carona( usuario.getId() , vagas, v , horario, dataa, destino, ajuda, lat, lon );

            CaronaDAO caronaDAO = CaronaFirebase.getInstance();//CaronaDBMemory.getInstance();
            UsuarioDAO usuarioDAO = UsuarioFirebase.getInstance();//UsuarioDBMemory.getInstance();

            usuario.addCarona( caronaDAO.addCarona(carona) );
            usuario = usuarioDAO.editUsuario(usuario);

        }else if( requestCode == RequestCodes.CAD_CARPOOL && resultCode == ResultCodes.CAD_CARPOOL_CANCEL ){

            Toast.makeText(this, "Carona Cancelada", Toast.LENGTH_SHORT).show();

        }

        if(requestCode == RequestCodes.MEUS_VEICULOS && resultCode == ResultCodes.MEUS_VEICULOS) {
            usuario = (Usuario) data.getSerializableExtra("usuario");
        }

        if(requestCode == RequestCodes.EDITAR_CONTA && resultCode == ResultCodes.MINHA_CONTA) {
            usuario = (Usuario) data.getSerializableExtra("usuario");
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @TargetApi(Build.VERSION_CODES.M)
    protected void askMapPermissions() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, RequestCodes.AFL_PERMISSION);
        }
        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, RequestCodes.ACL_PERMISSION);
        }
    }

    protected void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }

    protected void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Seu GPS parece estar desligado, deseja ativar?")
                .setCancelable(false)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
}