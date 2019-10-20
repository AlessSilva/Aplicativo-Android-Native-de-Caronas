package com.example.appcaronamobile.Util.CustomAdapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.appcaronamobile.R;

public class Situacoes_Adapter extends BaseAdapter implements SpinnerAdapter {
    private String[] situacoes;
    private Context context;

    public Situacoes_Adapter(Context context) {
        this.context = context;
        this.situacoes = new String[]{"Situação", "Docente", "Discente", "Outro"};
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
        return situacoes.length;
    }

    @Override
    public Object getItem(int position) {
        return situacoes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.situacao_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.situacao);

        if (position == 0) {
            textView.setTypeface(null, Typeface.ITALIC);
        }

        textView.setText(situacoes[position]);
        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view;
        view = View.inflate(context, R.layout.situacao_dropdown, null);
        final TextView textView = (TextView) view.findViewById(R.id.situacao_dropdown);

        if (position == 0) {
            textView.setTypeface(null, Typeface.ITALIC);
        }

        textView.setText(situacoes[position]);
        return view;
    }
}

