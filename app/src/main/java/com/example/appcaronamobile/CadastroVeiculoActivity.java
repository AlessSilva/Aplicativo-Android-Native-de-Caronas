package com.example.appcaronamobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.appcaronamobile.Model.Veiculo;
import com.example.appcaronamobile.Util.Codes.RequestCodes;
import com.example.appcaronamobile.Util.Codes.ResultCodes;
import com.example.appcaronamobile.Util.CustomAdapters.Tipos_Veiculos_Adapter;
import com.example.appcaronamobile.Util.Masks.MaskEditUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CadastroVeiculoActivity extends AppCompatActivity {
    private String senhaPedida = "";
    private String senhaDoUsuario;
    private EditText input;

    private EditText modeloV;
    private Spinner tipoV;
    private EditText corV;
    private EditText placaV;
    private ImageView mv;

    private String modelo;
    private String tipo;
    private String cor;
    private String placa;
    private String code;

    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imagemBytes;

    private Veiculo veiculo;

    private SpinnerAdapter spinnerAdapter;

    private int[] imgs_default = new int[] {R.mipmap.ic_car2, R.mipmap.ic_moto};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_veiculo);

        tipoV = findViewById(R.id.spinnerTipoVeiculo);
        spinnerAdapter = new Tipos_Veiculos_Adapter(this);
        tipoV.setAdapter(spinnerAdapter);


        modeloV = findViewById(R.id.editTextModeloCarro);
        corV = findViewById(R.id.editTextCorCarro);
        placaV = findViewById(R.id.editTextPlacaCarro);
        mv = findViewById(R.id.imageViewVeiculo);

        imagemBytes = null;

        placaV.addTextChangedListener(MaskEditUtil.mask(placaV, MaskEditUtil.FORMAT_PLACA));

        senhaDoUsuario = getIntent().getStringExtra("senha");
        code = getIntent().getStringExtra("code");
        String placa2 = getIntent().getStringExtra("placa");
        String modelo2 = getIntent().getStringExtra("modelo");
        String cor2 = getIntent().getStringExtra("cor");
        String tipo2 = getIntent().getStringExtra("tipo");

        if(code.equals(String.valueOf(RequestCodes.EDITAR_VEICULOS))) {
            String m = getIntent().getStringExtra("imagem");
            if(m.equals("n")) {
                if(tipo2.equals("Carro")) {
                    mv.setImageResource(imgs_default[0]);
                } else {
                    mv.setImageResource(imgs_default[1]);
                }
            } else {
                byte[] imagemByte = getIntent().getByteArrayExtra("valor");
                Bitmap bm = BitmapFactory.decodeByteArray(imagemByte, 0, imagemByte.length);
                mv.setImageBitmap(bm);
            }
        }

        modeloV.setText(modelo2);
        corV.setText(cor2);
        placaV.setText(placa2);
        tipoV.setSelection(Integer.valueOf(tipo2));

        Button finalizar = (Button) findViewById(R.id.buttonSalvarAlteracoesCarro);

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modelo = modeloV.getText().toString();
                tipo = tipoV.getSelectedItem().toString();
                cor = corV.getText().toString();
                placa = placaV.getText().toString();

                if(tipoV.getSelectedItemPosition() == 0 || modelo.equals("") || tipo.equals("") || cor.equals("") || placa.equals("")) {
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
                                veiculo = new Veiculo(modelo, tipo, placa, cor, imagemBytes);
                                Intent intent = new Intent();
                                intent.putExtra("veiculo", veiculo);
                                if(code.equals(String.valueOf(RequestCodes.ADD_VEICULO))) {
                                    setResult(ResultCodes.ADD_VEICULOS, intent);
                                    Toast.makeText(getApplicationContext(), "Veículo cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                                }
                                else if(code.equals(String.valueOf(RequestCodes.EDITAR_VEICULOS))) {
                                    setResult(ResultCodes.EDITAR_VEICULO, intent);
                                    Toast.makeText(getApplicationContext(), "Veículo editado com sucesso!", Toast.LENGTH_LONG).show();
                                }
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

        Button uploadImage = findViewById(R.id.uploadImageButtonVeiculo);
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            @TargetApi(Build.VERSION_CODES.M)
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, RequestCodes.CAMERA_PERMISSION);
                } else {
                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, RequestCodes.CAMERA_REQUEST);
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent();
            setResult(ResultCodes.CANCELAR, intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == RequestCodes.CAMERA_PERMISSION) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, RequestCodes.CAMERA_REQUEST);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RequestCodes.CAMERA_REQUEST) {
            if(resultCode == Activity.RESULT_OK) {
                Bitmap imagem = (Bitmap) data.getExtras().get("data");
                mv.setImageBitmap(imagem);
                byteArrayOutputStream = new ByteArrayOutputStream();
                imagem.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                imagemBytes = byteArrayOutputStream.toByteArray();
            }
        }
    }
}
