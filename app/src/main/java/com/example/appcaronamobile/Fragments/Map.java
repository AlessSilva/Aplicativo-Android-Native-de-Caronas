package com.example.appcaronamobile.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appcaronamobile.DBMemory.CaronaDBMemory;
import com.example.appcaronamobile.DBMemory.UsuarioDBMemory;
import com.example.appcaronamobile.Dao.CaronaDAO;
import com.example.appcaronamobile.Dao.UsuarioDAO;
import com.example.appcaronamobile.Firebase.CaronaFirebase;
import com.example.appcaronamobile.Firebase.UsuarioFirebase;
import com.example.appcaronamobile.Model.Carona;
import com.example.appcaronamobile.R;
import com.example.appcaronamobile.Util.CustomAdapters.MyAdapterMarkerInfo;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Map extends SupportMapFragment implements OnMapReadyCallback,
                    GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private GoogleMap mMap;
    FusedLocationProviderClient mFusedLocationClient;
    CaronaDAO caronaDAO = null;
    UsuarioDAO usuarioDAO = null;
    LatLng userLocation = null;
    Marker userMaker;

    private void initMapLocation(){

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this.getContext());

        try {

            Task location = mFusedLocationClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {

                    if ( task.isSuccessful() ){
                        Location l = (Location) task.getResult();
                        if( l != null ){

                            LatLng ll = new LatLng( l.getLatitude(), l.getLongitude() );
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom( ll , 15f));

                        }
                    }

                }
            });

        }catch ( SecurityException ex ){

        }

    }

    private void markerDeviceLocation(){

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this.getContext());

        try {

            Task location = mFusedLocationClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {

                    if ( task.isSuccessful() ){
                        Location l = (Location) task.getResult();
                        if( l != null ){

                            LatLng ll = new LatLng( l.getLatitude(), l.getLongitude() );
                            userLocation = ll;
                            userMaker = mMap.addMarker( new MarkerOptions().position(userLocation));

                        }
                    }

                }
            });

        }catch ( SecurityException ex ){

        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        caronaDAO = CaronaFirebase.getInstance();//CaronaDBMemory.getInstance();
        usuarioDAO = UsuarioFirebase.getInstance();//UsuarioDBMemory.getInstance();

        LatLng latLng;

        mMap.setInfoWindowAdapter(new MyAdapterMarkerInfo(getContext()));

        for(Carona c : caronaDAO.getListaCarona() ){

            latLng = new LatLng( c.getLatLocalEncontro(), c.getLngLocalEncontro() );

            if( c.getVeiculo().getTipo().equals("Carro") ){
                mMap.addMarker(new MarkerOptions().position(latLng)
                        .title( "Carona de: " + usuarioDAO.getUsuario( c.getId_responsavel() ).getPrimeiroNome() )
                         .snippet(usuarioDAO.getUsuario( c.getId_responsavel() ).getPrimeiroNome()+"<>"+
                                 usuarioDAO.getUsuario( c.getId_responsavel() ).getTelefone()+"<>"+
                                 c.getHorario()+"<>"+c.getDestino()+"<>"+(c.getVagas()-c.getConfirmados().size()))
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_car2)));
            }else {
                mMap.addMarker(new MarkerOptions().position(latLng)
                        .title( "Carona de: " + usuarioDAO.getUsuario( c.getId_responsavel() ).getPrimeiroNome() )
                        .snippet(usuarioDAO.getUsuario( c.getId_responsavel() ).getPrimeiroNome()+"<>"+
                                usuarioDAO.getUsuario( c.getId_responsavel() ).getTelefone()+"<>"+
                                c.getHorario()+"<>"+c.getDestino()+"<>"+(c.getVagas()-c.getConfirmados().size()))
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_moto)));
            }
        }

        mMap.setMapStyle( MapStyleOptions.loadRawResourceStyle(this.getContext(), R.raw.styler));

        initMapLocation();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}