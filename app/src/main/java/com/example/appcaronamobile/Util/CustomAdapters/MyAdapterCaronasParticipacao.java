package com.example.appcaronamobile.Util.CustomAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.example.appcaronamobile.R;

import java.util.List;

public class MyAdapterCaronasParticipacao extends RecyclerView.Adapter<MyAdapterCaronasParticipacao.CaronaViewHolder3> {


    private Context mContext;
    private List<Carona> listCarona;
    private ViewGroup parent;

    CaronaDAO caronaDAO = null;
    UsuarioDAO usuarioDAO = null;

    public MyAdapterCaronasParticipacao( Context mContext, List<Carona> listCarona  ){

        this.mContext = mContext;
        this.listCarona = listCarona;

        usuarioDAO = UsuarioDBMemory.getInstance();
        caronaDAO = CaronaDBMemory.getInstance();
    }

    @NonNull
    @Override
    public CaronaViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_participacao_caronas,parent,false);
        return new CaronaViewHolder3(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CaronaViewHolder3 holder, int position) {

        Carona carona = listCarona.get(position);
        holder.carona = carona;

        holder.data.setText( "Data: .../.../..." );
        holder.horario.setText("Horário: " + carona.getHorario());
        holder.destino.setText("Destino: " + carona.getDestino());

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

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( holder.estadoLike.equals("00") ){

                    holder.estadoLike = "10";
                    holder.carona.addLike();
                    holder.carona = caronaDAO.editCarona(holder.carona);

                    holder.like.setBackgroundColor( parent.getResources().getColor(android.R.color.holo_blue_light) );

                    Toast.makeText(mContext,"Like",Toast.LENGTH_SHORT).show();

                }else if ( holder.estadoLike.equals("10") ){

                    holder.estadoLike = "00";
                    holder.carona.removeLike();
                    holder.carona = caronaDAO.editCarona(holder.carona);

                    holder.like.setBackgroundColor( parent.getResources().getColor(android.R.color.white) );

                    Toast.makeText(mContext,"Like removido",Toast.LENGTH_SHORT).show();

                }

            }
        });

        holder.dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( holder.estadoLike.equals("00") ){

                    holder.estadoLike = "01";
                    holder.carona.addDislike();
                    holder.carona = caronaDAO.editCarona(holder.carona);

                    holder.dislike.setBackgroundColor( parent.getResources().getColor(android.R.color.holo_red_light) );

                    Toast.makeText(mContext,"Dislike",Toast.LENGTH_SHORT).show();

                }else if ( holder.estadoLike.equals("01") ){

                    holder.estadoLike = "00";
                    holder.carona.removeDislike();
                    holder.carona = caronaDAO.editCarona(holder.carona);

                    holder.dislike.setBackgroundColor( parent.getResources().getColor(android.R.color.white) );

                    Toast.makeText(mContext,"Dislike removido",Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return listCarona.size();
    }

    public class CaronaViewHolder3 extends RecyclerView.ViewHolder{

        Carona carona;

        View view = null;
        TextView data = null;
        TextView horario = null;
        TextView destino = null;
        ImageButton like = null;
        ImageButton dislike = null;

        View alertView = null;
        AlertDialog alertDialog = null;
        Button detalhes = null;

        String estadoLike = "00";

        public CaronaViewHolder3(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            data = view.findViewById( R.id.CardViewPCaronaData );
            horario = view.findViewById( R.id.CardViewPCaronaHorario );
            destino = view.findViewById( R.id.CardViewPCaronaDestino );
            like = view.findViewById( R.id.imageButtonPCLike );
            dislike = view.findViewById( R.id.imageButtonPCDislike );

            alertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detalhes_carona, parent,false);
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("Detalhes");
            builder.setView( alertView );
            alertDialog = builder.create();

            detalhes = view.findViewById( R.id.CardViewPCInfo );

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
