package com.example.appcaronamobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.appcaronamobile.Model.Carona;
import com.example.appcaronamobile.Model.Participante;
import com.example.appcaronamobile.Util.CustomAdapters.MyAdapterMinhasCaronas;
import com.example.appcaronamobile.Util.CustomAdapters.MyAdapterParticipantesCarona;

import java.util.List;

public class ParticipantesCaronaActivity extends AppCompatActivity {

    Carona carona;
    RecyclerView mRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participantes_carona);

        carona = (Carona) getIntent().getSerializableExtra("carona");

        mRecycleView = findViewById(R.id.recyclerParticipantesCaronas);
        GridLayoutManager gridLayoutManager = new GridLayoutManager( this,1 );
        mRecycleView.setLayoutManager(gridLayoutManager);

        MyAdapterParticipantesCarona adapterListParticipantes = new MyAdapterParticipantesCarona(carona,this,carona.getParticipantes());

        mRecycleView.setAdapter(adapterListParticipantes);
    }
}
