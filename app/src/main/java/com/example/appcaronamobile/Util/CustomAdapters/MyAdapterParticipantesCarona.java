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
import com.example.appcaronamobile.Firebase.CaronaFirebase;
import com.example.appcaronamobile.Model.Carona;
import com.example.appcaronamobile.Model.Participante;
import com.example.appcaronamobile.R;

import java.util.List;

public class MyAdapterParticipantesCarona extends RecyclerView.Adapter<MyAdapterParticipantesCarona.CaronaViewHolder4>{

    private Context mContext;
    private List<Participante> listParticipantes;
    private Carona carona;
    CaronaDAO caronaDAO = CaronaFirebase.getInstance();//CaronaDBMemory.getInstance();
    private  ViewGroup parent;

    public MyAdapterParticipantesCarona( Carona carona, Context mContext, List<Participante> listParticipantes  ){

        this.mContext = mContext;
        this.listParticipantes = listParticipantes;
        this.carona = carona;

    }

    @NonNull
    @Override
    public CaronaViewHolder4 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.parent = parent;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_participante_carona,parent,false);
        return new CaronaViewHolder4(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CaronaViewHolder4 holder, final int posintion) {

        holder.participante = listParticipantes.get(posintion);

        holder.nomeParticipante.setText( "@"+holder.participante.getNome() );

        if(holder.participante.isConfirmacao()){
            holder.confirmar.setText( "Confirmada" );
            holder.confirmar.setBackgroundColor( parent.getResources().getColor(android.R.color.holo_blue_light) );
            holder.ignorar.setEnabled(false);
            holder.ignorar.setBackgroundColor( parent.getResources().getColor(android.R.color.transparent) );
        }

        holder.ignorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    listParticipantes.remove(holder.getAdapterPosition());
                    notifyItemRemoved(holder.getAdapterPosition());
                    carona.setParticipantes(listParticipantes);
                    carona = caronaDAO.editCarona(carona);
                    notifyItemRangeChanged(holder.getAdapterPosition(),listParticipantes.size());

                    Toast.makeText(mContext,"Participante ignorado",Toast.LENGTH_SHORT).show();

            }
        });

        holder.confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.participante.setConfirmacao(true);
                listParticipantes.set( holder.getAdapterPosition(), holder.participante );
                notifyDataSetChanged();
                carona.setParticipantes(listParticipantes);
                carona = caronaDAO.editCarona(carona);
                holder.confirmar.setText( "Confirmada" );
                holder.confirmar.setBackgroundColor( parent.getResources().getColor(android.R.color.holo_blue_light) );
                holder.ignorar.setEnabled(false);
                holder.ignorar.setBackgroundColor( parent.getResources().getColor(android.R.color.transparent) );
                Toast.makeText(mContext,"Participante confirmado",Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return listParticipantes.size();
    }

    public class CaronaViewHolder4 extends RecyclerView.ViewHolder{

        View view;
        TextView nomeParticipante = null;
        Button confirmar = null;
        Button ignorar = null;
        Participante participante = null;

        public CaronaViewHolder4(@NonNull View itemView) {
            super(itemView);

            view = itemView;
            nomeParticipante = view.findViewById( R.id.CardViewParticipanteCaronaName );
            confirmar = view.findViewById( R.id.buttonCardViewParticipanteCaronaConfirmar );
            ignorar = view.findViewById(R.id.buttonCardViewParticipanteCaronaIgnorar);

        }

    }
}
