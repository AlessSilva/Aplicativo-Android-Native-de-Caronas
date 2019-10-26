package com.example.appcaronamobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcaronamobile.DBMemory.UsuarioDBMemory;
import com.example.appcaronamobile.Dao.UsuarioDAO;
import com.example.appcaronamobile.Model.Usuario;

public class MainActivity extends AppCompatActivity {

    UsuarioDAO usuarioDAO;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioDAO = UsuarioDBMemory.getInstance();

        intent = new Intent(this, CadastroUsuarioActivity.class);

        ((Button)findViewById(R.id.buttonCadastro)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(intent,111);
            }
        });

        ((Button)findViewById(R.id.buttonEntrarLogin)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String login = ((TextView) findViewById(R.id.editTextLogin)).getText()+"";
                String senha = ((TextView) findViewById(R.id.editTextSenhaLogin)).getText()+"";
                Usuario usuario = usuarioDAO.login(login,senha);
                if ( usuario == null ){
                    Toast.makeText( view.getContext(),"Usuário não cadastrado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText( view.getContext(),"Bem vindo " + usuario.getPrimeiroNome(), Toast.LENGTH_SHORT).show();
                    Logar(usuario);
                }
            }
        });
    }

    private void Logar(Usuario usuario){

        final Intent intent = new Intent(this, TelaPrincipalActivity.class);

        intent.putExtra("usuario", usuario);

        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode==111 && resultCode==112 ){

            String primeiroNome = data.getStringExtra("primeiro_nome");
            String sobrenome = data.getStringExtra("sobrenome");
            String telefone = data.getStringExtra("telefone");
            String email = data.getStringExtra("email");
            String senha = data.getStringExtra("senha");
            String situacao = data.getStringExtra("situacao");
            String instituicao = data.getStringExtra("instituicao");

            Usuario novoUsuario = new Usuario(primeiroNome, sobrenome, telefone, email, senha, situacao, instituicao);

            usuarioDAO.addUsuario( novoUsuario );

            Logar( novoUsuario );

        }else{

            Toast.makeText(this, "Cadastro Cancelado", Toast.LENGTH_SHORT).show();

        }

    }
}
