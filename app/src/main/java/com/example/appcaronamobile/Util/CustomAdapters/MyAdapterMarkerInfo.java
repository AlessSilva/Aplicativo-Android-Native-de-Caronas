package com.example.appcaronamobile.Util.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.appcaronamobile.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class MyAdapterMarkerInfo implements GoogleMap.InfoWindowAdapter {

    Context context;
    View view;

    public MyAdapterMarkerInfo(Context context) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.custom_marker_info,null);
    }

    private void setAllText( Marker marker ){

        String[] info = marker.getSnippet().split("<>");

        ((TextView)view.findViewById(R.id.textViewAlertCaronaUserNome)).setText( info[0] );
        ((TextView)view.findViewById(R.id.textViewAlertCaronaUserTelefone)).setText( info[1] );
        ((TextView)view.findViewById(R.id.textViewAlertCaronaHorario)).setText( info[2] );
        ((TextView)view.findViewById(R.id.textViewAlertCaronaDestino)).setText( info[3] );
        ((TextView)view.findViewById(R.id.textViewAlertCaronaVagasRestantes)).setText( info[4] );

    }

    @Override
    public View getInfoWindow(Marker marker) {
        setAllText(marker);
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        setAllText(marker);
        return view;
    }
}
