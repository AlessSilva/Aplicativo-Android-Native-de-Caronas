package com.example.appcaronamobile.Util.CustomAdapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.appcaronamobile.R;

public class Tipos_Veiculos_Adapter extends BaseAdapter implements SpinnerAdapter {
    private String[] tipos;
    private Context context;

    public Tipos_Veiculos_Adapter(Context context) {
        this.context = context;
        this.tipos = new String[] {"Tipos", "Carro", "Moto"};
    }

    @Override
    public boolean isEnabled(int position) {
        if (position == 0) {
            return false;
        }
        return true;
    }

    @Override
    public int getCount() {
        return tipos.length;
    }

    @Override
    public Object getItem(int position) {
        return tipos[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.veiculos_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.veiculos);

        if (position == 0) {
            textView.setTypeface(null, Typeface.ITALIC);
        }

        textView.setText(tipos[position]);
        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view;
        view = View.inflate(context, R.layout.veiculos_dropdown, null);
        final TextView textView = (TextView) view.findViewById(R.id.veiculos_dropdown);

        if (position == 0) {
            textView.setTypeface(null, Typeface.ITALIC);
        }

        textView.setText(tipos[position]);
        return view;
    }
}
