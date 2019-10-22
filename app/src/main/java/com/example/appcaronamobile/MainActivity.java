package com.example.appcaronamobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appcaronamobile.Model.Usuario;

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

            String primeiroNome = data.getStringExtra("primeiro_nome");
            String sobrenome = data.getStringExtra("sobrenome");
            String telefone = data.getStringExtra("telefone");
            String email = data.getStringExtra("email");
            String senha = data.getStringExtra("senha");
            String situacao = data.getStringExtra("situacao");
            String instituicao = data.getStringExtra("instituicao");

            //TODO: persistir usuario
            Usuario novoUsuario = new Usuario(primeiroNome, sobrenome, telefone, email, senha, situacao, instituicao);

            intent.putExtra("usuario", novoUsuario);

            startActivity(intent);

        }else{

            Toast.makeText(this, "Cadastro Cancelado", Toast.LENGTH_SHORT).show();

        }

    }
}
