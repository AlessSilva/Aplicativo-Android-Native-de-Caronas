package com.example.appcaronamobile.Fragments;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.provider.MediaStore;

import com.example.appcaronamobile.R;
import com.example.appcaronamobile.Repository.MyListener;
import com.example.appcaronamobile.Util.Codes.RequestCodes;
import com.example.appcaronamobile.Util.CustomAdapters.Instituicoes_Adapter;
import com.example.appcaronamobile.Util.CustomAdapters.Situacoes_Adapter;
import com.example.appcaronamobile.Util.RoundImage.RoundImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CadastroPt2 extends Fragment {

    View view = null;

    ImageView imageView = null;

    private Spinner spinnerInstituicoes=null;
    private Spinner spinnerSituacoes=null;

    private Button finalizar=null;
    private Button voltar=null;
    private Button uploadImage = null;

    private Bitmap imagem;
    private String imagemreal = null;

    public CadastroPt2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.activity_cadastro_pt2, container, false);

        imageView = (ImageView) view.findViewById(R.id.imageViewPerfil);
        imagem = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

        this.showInstituicoesSpinner();
        this.showSituacoesSpinner();

        finalizar = view.findViewById( R.id.buttonFinalizar );
        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spinnerInstituicoes = (Spinner) getView().findViewById(R.id.spinnerInstituicao);
                spinnerSituacoes    = (Spinner) getView().findViewById(R.id.spinnerSituacao);

                int posInst = spinnerInstituicoes.getSelectedItemPosition();
                int posSit = spinnerSituacoes.getSelectedItemPosition();

                if(posInst == 0 || posSit == 0) {
                    Toast.makeText(getContext(), "Selecione os campos que restam", Toast.LENGTH_LONG).show();
                } else {
                    String instituicao = spinnerInstituicoes.getSelectedItem().toString();
                    String situacao    = spinnerSituacoes.getSelectedItem().toString();
                    sendData(instituicao, situacao);
                }
            }
        });

        voltar = view.findViewById( R.id.buttonVoltarPt2 );
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });

        uploadImage = view.findViewById(R.id.uploadImageButton);

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, RequestCodes.GALLERY_REQUEST);
                } else {
                    startGallery();
                }
            }
        });

        return view;
    }

    protected void showInstituicoesSpinner() {
        spinnerInstituicoes = view.findViewById(R.id.spinnerInstituicao);
        Instituicoes_Adapter adapter_instituicoes = new Instituicoes_Adapter(getContext());
        spinnerInstituicoes.setAdapter(adapter_instituicoes);
    }

    protected void showSituacoesSpinner() {
        spinnerSituacoes = view.findViewById(R.id.spinnerSituacao);
        Situacoes_Adapter adaptar_situacoes = new Situacoes_Adapter(getContext());
        spinnerSituacoes.setAdapter(adaptar_situacoes);
    }

    private void sendData( String inst, String sit ){

        MyListener myListener = (MyListener)getActivity();
        myListener.finalizarFragmentoP2(inst,sit, imagemreal);

    }

    private void back(){
        MyListener myListener = (MyListener)getActivity();
        myListener.voltarFragmentoP2();
    }

    private void startGallery() {
        Intent cameraIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        cameraIntent.setType("image/*");
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(cameraIntent, RequestCodes.GALLERY_REQUEST);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RequestCodes.GALLERY_REQUEST) {
            if(resultCode == Activity.RESULT_OK) {
                Uri returnUri = data.getData();
                try {
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };

                    Cursor cursor = getContext().getContentResolver().query(returnUri,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imagemreal = cursor.getString(columnIndex);

                    cursor.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                imageView.setImageBitmap(BitmapFactory.decodeFile(imagemreal));
            }
        }
    }
}
