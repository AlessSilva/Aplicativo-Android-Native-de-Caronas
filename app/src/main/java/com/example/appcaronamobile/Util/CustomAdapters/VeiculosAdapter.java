package com.example.appcaronamobile.Util.CustomAdapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.appcaronamobile.Model.Veiculo;

import java.util.List;

public class VeiculosAdapter extends BaseAdapter {
    private Context context;
    private List<Veiculo> veiculos;

    public VeiculosAdapter(Context context, List<Veiculo> veiculos) {
        this.context = context;
        this.veiculos = veiculos;
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
        TextView tv = new TextView(context);
        tv.setText(veiculos.get(position).toString());
        tv.setTextSize(20);
        tv.setTextColor(Color.parseColor("#9E9D24"));
        return tv;
    }

    public void remove(int position) {
        veiculos.remove(position);
    }
}
