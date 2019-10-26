package com.example.appcaronamobile.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.example.appcaronamobile.R;
import com.example.appcaronamobile.Repository.MyListener2;
import com.example.appcaronamobile.Repository.MyListener3;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Map2 extends SupportMapFragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMapClickListener{

    private GoogleMap mMap;
    private LatLng latLng;
    FusedLocationProviderClient mFusedLocationClient;
    private Marker marker;

    private void getDeviceLocation(){

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this.getContext());

        try {

            Task location = mFusedLocationClient.getLastLocation();
            location.addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {

                    if ( task.isSuccessful() ){
                        Location l = (Location) task.getResult();

                        if( l != null ){

                            latLng = new LatLng( l.getLatitude(), l.getLongitude() );
                            sendLatLgt();
                            marker = mMap.addMarker(new MarkerOptions().position(latLng).title("Local de Encontro"));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom( latLng , 15f));

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
        mMap.setOnMapClickListener(this);
        //mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setMapStyle( MapStyleOptions.loadRawResourceStyle(this.getContext(), R.raw.styler));
        getDeviceLocation();
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

    @Override
    public void onMapClick(LatLng latLng) {

        this.latLng = latLng;
        sendLatLgt();
        marker.remove();
        marker = mMap.addMarker(new MarkerOptions().position(latLng).title("Local de Encontro"));

    }

    private void sendLatLgt(){

        MyListener2 myListener2 = (MyListener2) getActivity();
        myListener2.receberLatLng(latLng);

    }

}
