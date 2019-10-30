package com.example.appcaronamobile.Util.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appcaronamobile.DBMemory.CaronaDBMemory;
import com.example.appcaronamobile.DBMemory.UsuarioDBMemory;
import com.example.appcaronamobile.Dao.CaronaDAO;
import com.example.appcaronamobile.Dao.UsuarioDAO;
import com.example.appcaronamobile.Model.Carona;
import com.example.appcaronamobile.Model.Participante;
import com.example.appcaronamobile.R;

import java.util.List;

public class MyAdapterListarCaronas extends RecyclerView.Adapter<MyAdapterListarCaronas.CaronaViewHolder>{

    private Context mContext;
    private List<Carona> listCarona;
    private ViewGroup parent;

    UsuarioDAO usuarioDAO = null;
    CaronaDAO caronaDAO = null;

    public MyAdapterListarCaronas( Context mContext, List<Carona> listCarona  ){

        this.mContext = mContext;
        this.listCarona = listCarona;

        usuarioDAO = UsuarioDBMemory.getInstance();
        caronaDAO = CaronaDBMemory.getInstance();
    }

    @NonNull
    @Override
    public CaronaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_carona,parent,false);
        return new CaronaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CaronaViewHolder holder, final int position) {

        holder.carona = listCarona.get(position);

        holder.responsavel.setText( "@"+usuarioDAO.getUsuario( holder.carona.getId_responsavel() ).getPrimeiroNome() );
        holder.horario.setText("Horário: "+holder.carona.getHorario());
        holder.destino.setText("Destino: "+holder.carona.getDestino());

        holder.participar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( (holder.carona.getVagas() - holder.carona.getConfirmados().size()) > 0 ){

                    holder.carona.addUsuario( new Participante( usuarioDAO.getLogado(), "") );
                    holder.carona = caronaDAO.editCarona(holder.carona);

                    listCarona.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                    notifyItemRangeChanged(holder.getAdapterPosition(),listCarona.size());

                    Toast.makeText(mContext,"Aguarde a confirmação do responsável",Toast.LENGTH_SHORT).show();

                }

            }
        });

        if ( holder.carona.getVeiculo().getTipo().equals("Carro") ){
            holder.tipoveiculo.setImageResource(R.mipmap.ic_car2);

            ( (ImageView) holder.alertView.findViewById( R.id.imageViewAlertCarona )).setImageResource( R.mipmap.ic_car2 );

        }else{
            holder.tipoveiculo.setImageResource(R.mipmap.ic_moto);

            ( (ImageView) holder.alertView.findViewById( R.id.imageViewAlertCarona )).setImageResource( R.mipmap.ic_moto );

        }
        if ( holder.carona.isAjuda() ){
            holder.ajuda.setImageResource(R.mipmap.ic_ajuda);
        }

        //------------------------------------- Campos do Alert ----------------------------------------------

        ( (TextView) holder.alertView.findViewById( R.id.textViewAlertCaronaUserNome ))
                .setText( usuarioDAO.getUsuario( holder.carona.getId_responsavel() ).getPrimeiroNome() +" "+ usuarioDAO.getUsuario( holder.carona.getId_responsavel() ).getSobrenome() );

        ( (TextView) holder.alertView.findViewById( R.id.textViewAlertCaronaUserInsituicao ))
                .setText( usuarioDAO.getUsuario( holder.carona.getId_responsavel() ).getInstituicao() );

        ( (TextView) holder.alertView.findViewById( R.id.textViewAlertCaronaUserSituacao ))
                .setText( usuarioDAO.getUsuario( holder.carona.getId_responsavel() ).getSituacao() );

        ( (TextView) holder.alertView.findViewById( R.id.textViewAlertCaronaUserTelefone ))
                .setText( usuarioDAO.getUsuario( holder.carona.getId_responsavel() ).getTelefone() );

        ( (TextView) holder.alertView.findViewById( R.id.textViewAlertCaronaVagasTotal ))
                .setText( holder.carona.getVagas()+"" );

        ( (TextView) holder.alertView.findViewById( R.id.textViewAlertCaronaVagasRestantes ))
                .setText( (holder.carona.getVagas()-holder.carona.getConfirmados().size())+"" );

        ( (TextView) holder.alertView.findViewById( R.id.textViewAlertCaronaHorario ))
                .setText( holder.carona.getHorario() );

        ( (TextView) holder.alertView.findViewById( R.id.textViewAlertCaronaDestino ))
                .setText( holder.carona.getDestino() );

        ( (TextView) holder.alertView.findViewById( R.id.textViewAlertCaronaVeiculo ))
                .setText( holder.carona.getVeiculo().getModelo() );

        ( (TextView) holder.alertView.findViewById( R.id.textViewAlertCaronaVeiculoPlaca ))
                .setText( holder.carona.getVeiculo().getPlaca() );

    }

    @Override
    public int getItemCount() {
        return listCarona.size();
    }

    public class CaronaViewHolder extends RecyclerView.ViewHolder{

        View view = null;
        TextView horario = null;
        TextView destino = null;
        TextView responsavel = null;
        ImageView tipoveiculo = null;
        ImageView ajuda = null;
        View alertView = null;
        AlertDialog alertDialog = null;
        Button detalhes = null;
        Button participar = null;
        Carona carona;

        public CaronaViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;

            horario = view.findViewById(R.id.CardViewCaronaHorario);
            destino = view.findViewById(R.id.CardViewCaronaDestino);
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

            ((Button)alertView.findViewById(R.id.buttonAlertCaronaFechar)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                }
            });

            participar = view.findViewById( R.id.buttonCardViewParticipar );

        }

    }
}
