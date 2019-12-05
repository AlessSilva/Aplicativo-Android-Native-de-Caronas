package com.example.appcaronamobile.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.appcaronamobile.DBMemory.CaronaDBMemory;
import com.example.appcaronamobile.DBMemory.UsuarioDBMemory;
import com.example.appcaronamobile.Dao.CaronaDAO;
import com.example.appcaronamobile.Dao.UsuarioDAO;
import com.example.appcaronamobile.Firebase.CaronaFirebase;
import com.example.appcaronamobile.Firebase.UsuarioFirebase;
import com.example.appcaronamobile.Model.Carona;
import com.example.appcaronamobile.Model.Participante;
import com.example.appcaronamobile.Model.Usuario;
import com.example.appcaronamobile.R;
import com.example.appcaronamobile.Util.CustomAdapters.MyAdapterListarCaronas;

import java.util.ArrayList;
import java.util.List;

public class ListarCaronas extends Fragment {

    View view = null;
    List<Carona> caronas;

    UsuarioDAO usuarioDAO  = null;
    CaronaDAO caronaDAO = null;

    Usuario usuario = null;

    public ListarCaronas() {
    }

    RecyclerView mRecycleView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_listar_caronas, container, false);

        caronaDAO = CaronaFirebase.getInstance();//CaronaDBMemory.getInstance();
        usuarioDAO = UsuarioFirebase.getInstance();//UsuarioDBMemory.getInstance();

        Bundle arguments = getArguments();
        usuario = (Usuario) arguments.getSerializable("usuario");

        mRecycleView = view.findViewById(R.id.recyclerCaronas);
        GridLayoutManager gridLayoutManager = new GridLayoutManager( this.getContext(),1 );
        mRecycleView.setLayoutManager(gridLayoutManager);

        caronas = new ArrayList<Carona>();

        Toast.makeText(view.getContext(), usuario.getId()+"", Toast.LENGTH_SHORT).show();

        for( Carona c : caronaDAO.getListaCarona() ){

            if( (c.getParticipante(usuario) == null) && (!c.getId_responsavel().equals(usuario.getId())) ){
                caronas.add(c);
            }
        }


        MyAdapterListarCaronas adapterListCaronas = new MyAdapterListarCaronas(this.getContext(),caronas);

        mRecycleView.setAdapter(adapterListCaronas);

        return view;
    }

}
