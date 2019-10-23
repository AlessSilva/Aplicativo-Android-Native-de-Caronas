package com.example.appcaronamobile.Repository;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcaronamobile.Model.Carona;
import com.example.appcaronamobile.R;

import java.util.List;

public class MyAdapterListarCaronas extends RecyclerView.Adapter<MyAdapterListarCaronas.CaronaViewHolder>{

    private Context mContext;
    private List<Carona> listCarona;

    public MyAdapterListarCaronas( Context mContext, List<Carona> listCarona  ){

        this.mContext = mContext;
        this.listCarona = listCarona;

    }

    @NonNull
    @Override
    public CaronaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_carona,parent,false);
        return new CaronaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaronaViewHolder holder, int position) {

        Carona carona = listCarona.get(position);
        holder.vagas.setText(carona.getVagas()+"");
        holder.perfil.setImageResource( R.drawable.galinha );
        if ( carona.gettipoVeiculo().equals("CARRO") ){
            holder.tipoveiculo.setImageResource(R.drawable.ic_car);
        }else{
            holder.tipoveiculo.setImageResource(R.drawable.ic_moto);
        }

    }

    @Override
    public int getItemCount() {
        return listCarona.size();
    }

    public class CaronaViewHolder extends RecyclerView.ViewHolder{

        View view;
        TextView vagas =  null;
        ImageView perfil = null;
        ImageView tipoveiculo = null;

        public CaronaViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;

            vagas = view.findViewById(R.id.CardViewCaronaVagas);
            perfil = view.findViewById(R.id.CardViewCaronaImageViewPerfil);
            tipoveiculo = view.findViewById(R.id.CardViewCaronaVeiculo);

        }
    }
}
