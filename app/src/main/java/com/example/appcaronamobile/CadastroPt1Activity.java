package com.example.appcaronamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcaronamobile.Util.Masks.MaskEditUtil;

public class CadastroPt1Activity extends AppCompatActivity {

    private EditText primeiroNome;
    private EditText sobrenome;
    private EditText telefone;
    private EditText email;
    private EditText senha;
    private EditText senhaRepetida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pt1);

        primeiroNome = findViewById(R.id.editTextPrimeiroNome);
        sobrenome = findViewById(R.id.editTextSobrenome);
        telefone = findViewById(R.id.editTextTelefone);

        telefone.addTextChangedListener(MaskEditUtil.mask(telefone, MaskEditUtil.FORMAT_PHONE));

        email = findViewById(R.id.editTextEmail);
        senha = findViewById(R.id.editTextSenha);
        senhaRepetida = findViewById(R.id.editTextSenhaRepetida);
    }

    public void onClickVoltar(View view) {
        finish();
    }

    public void onClickProximo(View view) {
        String pn = primeiroNome.getText().toString();
        String sb = sobrenome.getText().toString();
        String tel = telefone.getText().toString();
        String eml = email.getText().toString();
        String s1 = senha.getText().toString();
        String s2 = senhaRepetida.getText().toString();

        if (pn.equals("") || sb.equals("") || tel.equals("") ||
                eml.equals("") || s1.equals("") || s2.equals("")) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_LONG).show();
        } else if (!s1.equals(s2)) {
            Toast.makeText(this, "Senhas estão diferentes!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, CadastroPt2Activity.class);

            intent.putExtra("primeiro_nome", pn);
            intent.putExtra("sobrenome", sb);
            intent.putExtra("telefone", tel);
            intent.putExtra("email", eml);
            intent.putExtra("senha", s1);

            startActivity(intent);
        }
    }
}