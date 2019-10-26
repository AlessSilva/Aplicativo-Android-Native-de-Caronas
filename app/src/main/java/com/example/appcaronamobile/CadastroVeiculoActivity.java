package com.example.appcaronamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcaronamobile.Model.Usuario;
import com.example.appcaronamobile.Model.Veiculo;

public class CadastroVeiculoActivity extends AppCompatActivity {
    private String senhaPedida = "";
    private EditText input;

    private EditText modeloV;
    private EditText tipoV;
    private EditText corV;
    private EditText placaV;

    private String modelo;
    private String tipo;
    private String cor;
    private String placa;

    private Veiculo veiculo;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_veiculo);

        usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        modeloV = findViewById(R.id.editTextModeloCarro);
        tipoV = findViewById(R.id.editTextTipoCarro);
        corV = findViewById(R.id.editTextCorCarro);
        placaV = findViewById(R.id.editTextPlacaCarro);

        Button finalizar = (Button) findViewById(R.id.buttonSalvarAlteracoesCarro);

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelo = modeloV.getText().toString();
                tipo = tipoV.getText().toString();
                cor = corV.getText().toString();
                placa = placaV.getText().toString();

                if(modelo.equals("") || tipo.equals("") || cor.equals("") || placa.equals("")) {
                    Toast.makeText(view.getContext(), "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Digite sua antiga senha");
                    input = new EditText(view.getContext());
                    input.setTransformationMethod(new PasswordTransformationMethod());
                    builder.setView(input);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            senhaPedida = input.getText().toString();
                            if(usuario.getSenha().equals(senhaPedida)) {
                                veiculo = new Veiculo(modelo, tipo, placa, cor);
                                usuario.addVeiculo(veiculo);
                                Toast.makeText(getApplicationContext(), "Veículo cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Senha incorreta", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();
                }
            }
        });
    }

}
