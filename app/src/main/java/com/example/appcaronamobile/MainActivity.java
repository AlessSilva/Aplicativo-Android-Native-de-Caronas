package com.example.appcaronamobile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcaronamobile.Util.Codes.RequestCodes;
import com.example.appcaronamobile.DBMemory.UsuarioDBMemory;
import com.example.appcaronamobile.Dao.UsuarioDAO;
import com.example.appcaronamobile.Model.Usuario;
import com.example.appcaronamobile.Util.Codes.ResultCodes;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    UsuarioDAO usuarioDAO = null;
    Button buttonCadastro = null;
    Button buttonLogin = null;

    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuarioDAO = UsuarioDBMemory.getInstance();

        intent = new Intent(this, CadastroUsuarioActivity.class);

        buttonCadastro = (Button)findViewById(R.id.buttonCadastro);
        buttonLogin = (Button)findViewById(R.id.buttonEntrarLogin);

        buttonCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(intent, RequestCodes.CAD_USER);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String login = ((TextView) findViewById(R.id.editTextLogin)).getText()+"";
                String senha = ((TextView) findViewById(R.id.editTextSenhaLogin)).getText()+"";

                Usuario usuario = usuarioDAO.login(login,senha);

                if ( usuario == null ){
                    Toast.makeText( view.getContext(),"Usuário não encontrado", Toast.LENGTH_SHORT).show();
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

        if( requestCode==RequestCodes.CAD_USER && resultCode== ResultCodes.CAD_USER_SUCESS ){

            String primeiroNome = data.getStringExtra("primeiro_nome");
            String sobrenome = data.getStringExtra("sobrenome");
            String telefone = data.getStringExtra("telefone");
            String email = data.getStringExtra("email");
            String senha = data.getStringExtra("senha");
            String situacao = data.getStringExtra("situacao");
            String instituicao = data.getStringExtra("instituicao");
            byte [] bs = data.getByteArrayExtra("byteArray");

            Usuario novoUsuario = new Usuario(primeiroNome, sobrenome, telefone, email, senha, situacao, instituicao, bs);

            usuarioDAO.addUsuario( novoUsuario );

            Logar( novoUsuario );

        }else if ( requestCode==RequestCodes.CAD_USER && resultCode== ResultCodes.CAD_USER_CANCEL){

            Toast.makeText(this, "Cadastro Cancelado", Toast.LENGTH_SHORT).show();

        }

    }
}
