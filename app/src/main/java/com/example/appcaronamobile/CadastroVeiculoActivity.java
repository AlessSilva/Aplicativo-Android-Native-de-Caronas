package com.example.appcaronamobile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcaronamobile.Model.Veiculo;
import com.example.appcaronamobile.Util.Masks.MaskEditUtil;

public class CadastroVeiculoActivity extends AppCompatActivity {
    private String senhaPedida = "";
    private String senhaDoUsuario;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_veiculo);

        modeloV = findViewById(R.id.editTextModeloCarro);
        tipoV = findViewById(R.id.editTextTipoCarro);
        corV = findViewById(R.id.editTextCorCarro);
        placaV = findViewById(R.id.editTextPlacaCarro);

        placaV.addTextChangedListener(MaskEditUtil.mask(placaV, MaskEditUtil.FORMAT_PLACA));

        senhaDoUsuario = getIntent().getStringExtra("senha");

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
                    builder.setTitle("Digite sua senha");
                    input = new EditText(view.getContext());
                    input.setTransformationMethod(new PasswordTransformationMethod());
                    builder.setView(input);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            senhaPedida = input.getText().toString();
                            if(senhaDoUsuario.equals(senhaPedida)) {
                                veiculo = new Veiculo(modelo, tipo, placa, cor);
                                Intent intent = new Intent();
                                intent.putExtra("veiculo", veiculo);
                                setResult(222, intent);
                                Toast.makeText(getApplicationContext(), "Veículo cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                                finish();
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            setResult(333, intent);
        }
        return super.onKeyDown(keyCode, event);
    }

}
