package com.example.appcaronamobile.Repository;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcaronamobile.DBMemory.UsuarioDBMemory;
import com.example.appcaronamobile.Dao.UsuarioDAO;
import com.example.appcaronamobile.Model.Carona;
import com.example.appcaronamobile.R;

import java.util.List;

public class MyAdapterListarCaronas extends RecyclerView.Adapter<MyAdapterListarCaronas.CaronaViewHolder>{

    private Context mContext;
    private List<Carona> listCarona;
    private ViewGroup parent;

    UsuarioDAO usuarioDAO = null;

    public MyAdapterListarCaronas( Context mContext, List<Carona> listCarona  ){

        this.mContext = mContext;
        this.listCarona = listCarona;

        usuarioDAO = UsuarioDBMemory.getInstance();
    }

    @NonNull
    @Override
    public CaronaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_carona,parent,false);
        return new CaronaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaronaViewHolder holder, int position) {

        Carona carona = listCarona.get(position);

        holder.vagas.setText( "Vagas: " + (carona.getVagas()-carona.getParticipantes().size()) + " de "+carona.getVagas());
        holder.responsavel.setText( "@"+usuarioDAO.getUsuario( carona.getId_responsavel() ).getPrimeiroNome() );

        ( (TextView) holder.alertView.findViewById( R.id.textViewAlertCaronaUserNome ))
                .setText( usuarioDAO.getUsuario( carona.getId_responsavel() ).getPrimeiroNome() +" "+ usuarioDAO.getUsuario( carona.getId_responsavel() ).getSobrenome() );

        ( (TextView) holder.alertView.findViewById( R.id.textViewAlertCaronaUserInsituicao ))
                .setText( usuarioDAO.getUsuario( carona.getId_responsavel() ).getInstituicao() );

        ( (TextView) holder.alertView.findViewById( R.id.textViewAlertCaronaUserSituacao ))
                .setText( usuarioDAO.getUsuario( carona.getId_responsavel() ).getSituacao() );

        ( (TextView) holder.alertView.findViewById( R.id.textViewAlertCaronaUserTelefone ))
                .setText( usuarioDAO.getUsuario( carona.getId_responsavel() ).getTelefone() );

        ( (TextView) holder.alertView.findViewById( R.id.textViewAlertCaronaVagasTotal ))
                .setText( carona.getVagas()+"" );

        ( (TextView) holder.alertView.findViewById( R.id.textViewAlertCaronaVagasRestantes ))
                .setText( (carona.getVagas()-carona.getParticipantes().size())+"" );

        ( (TextView) holder.alertView.findViewById( R.id.textViewAlertCaronaHorario ))
                .setText( carona.getHorario() );

        ( (TextView) holder.alertView.findViewById( R.id.textViewAlertCaronaDestino ))
                .setText( carona.getDestino() );

        ( (TextView) holder.alertView.findViewById( R.id.textViewAlertCaronaVeiculo ))
                .setText( carona.getVeiculo().getModelo() );

        ( (TextView) holder.alertView.findViewById( R.id.textViewAlertCaronaVeiculoPlaca ))
                .setText( carona.getVeiculo().getPlaca() );

        if ( carona.getVeiculo().getTipo().equals("CARRO") ){
            holder.tipoveiculo.setImageResource(R.mipmap.ic_car2);

            ( (ImageView) holder.alertView.findViewById( R.id.imageViewAlertCarona )).setImageResource( R.mipmap.ic_car2 );

        }else{
            holder.tipoveiculo.setImageResource(R.mipmap.ic_moto);

            ( (ImageView) holder.alertView.findViewById( R.id.imageViewAlertCarona )).setImageResource( R.mipmap.ic_moto );

        }
        if ( carona.isAjuda() ){
            holder.ajuda.setImageResource(R.mipmap.ic_ajuda);
        }

    }

    @Override
    public int getItemCount() {
        return listCarona.size();
    }

    public class CaronaViewHolder extends RecyclerView.ViewHolder{

        View view = null;
        TextView vagas =  null;
        TextView responsavel = null;
        ImageView tipoveiculo = null;
        ImageView ajuda = null;
        View alertView = null;
        AlertDialog alertDialog = null;
        Button detalhes = null;

        public CaronaViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;

            vagas = view.findViewById(R.id.CardViewCaronaVagas);
            responsavel = view.findViewById(R.id.CardViewCaronaUserName);
            tipoveiculo = view.findViewById(R.id.CardViewCaronaVeiculo);
            ajuda = view.findViewById(R.id.CardViewCaronaAjuda);

            alertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detalhes_carona, parent,false);
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("Detalhes");
            builder.setView( alertView );
            alertDialog = builder.create();

            detalhes = view.findViewById( R.id.CardViewCaronaCaronaInfo );
            detalhes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.show();
                }
            });

        }

        private void Detalhes(){

            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("Detalhes");
            builder.setView( alertView );
            alertDialog = builder.create();
            alertDialog.show();

        }

    }
}
