package com.example.appcaronamobile.Util.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcaronamobile.DBMemory.UsuarioDBMemory;
import com.example.appcaronamobile.Dao.UsuarioDAO;
import com.example.appcaronamobile.Model.Carona;
import com.example.appcaronamobile.R;

import java.util.List;

public class MyAdapterMinhasCaronas extends RecyclerView.Adapter<MyAdapterMinhasCaronas.CaronaViewHolder2> {


    private Context mContext;
    private List<Carona> listCarona;
    private ViewGroup parent;

    UsuarioDAO usuarioDAO = null;

    public MyAdapterMinhasCaronas( Context mContext, List<Carona> listCarona  ){

        this.mContext = mContext;
        this.listCarona = listCarona;

        usuarioDAO = UsuarioDBMemory.getInstance();
    }

    @NonNull
    @Override
    public CaronaViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_minhas_caronas,parent,false);
        return new CaronaViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CaronaViewHolder2 holder, int position) {

        Carona carona = listCarona.get(position);

        holder.data.setText( "Data: .../.../..." );
        holder.horario.setText("Horário: " + carona.getHorario());
        holder.destino.setText("Destino: " + carona.getDestino());
        holder.like.setText(carona.getLikes()+"");
        holder.dislike.setText(carona.getDislikes()+"");

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



    }

    @Override
    public int getItemCount() {
        return listCarona.size();
    }

    public class CaronaViewHolder2 extends RecyclerView.ViewHolder{


        View view = null;
        TextView data = null;
        TextView horario = null;
        TextView destino = null;
        TextView like = null;
        TextView dislike = null;

        View alertView = null;
        AlertDialog alertDialog = null;
        Button detalhes = null;

        public CaronaViewHolder2(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            data = view.findViewById( R.id.CardViewMCaronaData );
            horario = view.findViewById( R.id.CardViewMCaronaHorario );
            destino = view.findViewById( R.id.CardViewMCaronaDestino );
            like = view.findViewById( R.id.textViewMCLike );
            dislike = view.findViewById( R.id.textViewMCDislike );

            alertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detalhes_carona, parent,false);
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("Detalhes");
            builder.setView( alertView );
            alertDialog = builder.create();

            detalhes = view.findViewById( R.id.CardViewMCInfo );

            //Toast.makeText(parent.getContext(), detalhes+"", Toast.LENGTH_SHORT).show();
            detalhes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.show();
                }
            });

        }

    }

}
