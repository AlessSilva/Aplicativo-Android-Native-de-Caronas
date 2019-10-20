package com.example.appcaronamobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, CadastroUsuarioActivity.class);

        ((Button)findViewById(R.id.buttonCadastro)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(intent,111);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode==111 && resultCode==112 ){

            Intent intent = new Intent(this, TelaPrincipalActivity.class);

            //TODO: enviar os valores do cadastro pra TelaPrincipal

            startActivity(intent);

        }else{

            Toast.makeText(this, "Cadastro Cancelado", Toast.LENGTH_SHORT).show();

        }

    }
}
