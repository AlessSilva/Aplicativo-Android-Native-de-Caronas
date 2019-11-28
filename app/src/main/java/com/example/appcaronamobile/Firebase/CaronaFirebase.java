package com.example.appcaronamobile.Firebase;

import androidx.annotation.NonNull;

import com.example.appcaronamobile.Dao.CaronaDAO;
import com.example.appcaronamobile.Model.Carona;
import com.example.appcaronamobile.Model.Participante;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class CaronaFirebase implements CaronaDAO {

    private static ArrayList<Carona> caronas;
    private static CaronaFirebase caronaDAO;
    private static ConexaoDB conexaoDB = MyConexaoDB.getInstance();

    public static boolean atualiza_mapa = false;

    private CaronaFirebase(){

        caronas = new ArrayList<>();

        Query query = conexaoDB.getReference().child("Carona").orderByChild("data");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                caronas.clear();

                for( DataSnapshot obj : dataSnapshot.getChildren() ){

                    caronas.add( obj.getValue(Carona.class) );

                }

                atualiza_mapa = true;

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public static CaronaDAO getInstance(){

        if( caronaDAO == null ){
            caronaDAO = new CaronaFirebase();
        }

        return caronaDAO;
    }

    @Override
    public Carona addCarona(Carona carona) {
        carona.setId(UUID.randomUUID().toString());
        conexaoDB.getReference().child("Carona").child(carona.getId()).setValue(carona);

        return carona;
    }

    @Override
    public Carona editCarona(Carona carona) {
        conexaoDB.getReference().child("Carona").child(carona.getId()).setValue(carona);
        return carona;
    }

    @Override
    public void deleteCarona(String caronaId) {

        conexaoDB.getReference().child("Carona").child(caronaId).removeValue();

    }

    @Override
    public Carona getCarona(String caronaId) {

        for( Carona c: caronas ){
            if(c.getId().equals(caronaId)){
                return c;
            }
        }

        return null;
    }

    @Override
    public ArrayList<Carona> getListaCarona() {
        return caronas;
    }

    @Override
    public ArrayList<Carona> getListaCarona(String usuario) {

        ArrayList<Carona> caronas1 = new ArrayList<>();
        for( Carona c : caronas ){

            if( c.getId_responsavel().equals(usuario) ){

                caronas1.add(c);

            }

        }

        return caronas1;
    }

    @Override
    public ArrayList<Carona> getListaCaronaParticipacao(String usuario) {

        ArrayList<Carona> caronas1 = new ArrayList<>();
        for( Carona c : caronas ){

            for(Participante p : c.getParticipantes()){

                if( p.getId().equals(usuario) ) {
                    caronas1.add(c);
                }

            }

        }

        return caronas1;
    }
}
