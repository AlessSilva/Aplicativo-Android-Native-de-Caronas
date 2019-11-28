package com.example.appcaronamobile.Util.CustomAdapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appcaronamobile.Model.Veiculo;
import com.example.appcaronamobile.R;

import java.util.ArrayList;
import java.util.List;

public class VeiculosAdapter extends BaseAdapter {
    private Activity context;
    private List<Veiculo> veiculos;
    private int[] imgs_default;

    public VeiculosAdapter(Activity context, List<Veiculo> veiculos) {
        this.context = context;
        this.veiculos = veiculos;
        this.imgs_default = new int[] {R.mipmap.ic_car2, R.mipmap.ic_moto};
    }

    @Override
    public int getCount() {
        return veiculos.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return veiculos.get(position);
    }

    @Override
    public View getView(int position, View contextView, ViewGroup parent) {
        LayoutInflater li = context.getLayoutInflater();
        View rowView = li.inflate(R.layout.custon_listview_layout, null, true);
        TextView tv = rowView.findViewById(R.id.item);
        ImageView iv = rowView.findViewById(R.id.iconVeiculo);
        TextView tv2 = rowView.findViewById(R.id.sub);

        String [] content = veiculos.get(position).toString().split("\n");

        String modelo = content[0];
        String tipo = content[1];
        String cor = content[2];
        String placa = content[3];

        tv.setText(modelo + "\n" + placa);
        tv.setTextSize(15);
        tv.setTextColor(Color.parseColor("#1565C0"));

        tv2.setText(tipo + "\n" + cor);
        tv2.setTextSize(12);
        tv2.setTextColor(Color.parseColor("#0A0A0A"));

        if(veiculos.get(position).getImagem() == null) {
            if(veiculos.get(position).getTipo().equals("Carro")) {
                iv.setImageResource(imgs_default[0]);
            } else if(veiculos.get(position).getTipo().equals("Moto")) {
                iv.setImageResource(imgs_default[1]);
            }
        } else {
//            byte[] imagemByte = veiculos.get(position).getImagem();
            Bitmap bm = this.StringToBitMap(veiculos.get(position).getImagem());
            iv.setImageBitmap(bm);
        }

        return rowView;
    }

    public int getByTipo(String tipo) {
        if(tipo.equals("Carro")) {
            return 1;
        }
        else if(tipo.equals("Moto")) {
            return 2;
        }
        return 0;
    }

    public void remove(int position) {
        veiculos.remove(position);
    }

    private Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte=Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}