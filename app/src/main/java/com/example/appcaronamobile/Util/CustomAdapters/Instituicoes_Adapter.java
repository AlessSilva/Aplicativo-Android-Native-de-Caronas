package com.example.appcaronamobile.Util.CustomAdapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.appcaronamobile.R;

public class Instituicoes_Adapter extends BaseAdapter implements SpinnerAdapter {
    private String[] instituicoes;
    private Context context;

    public Instituicoes_Adapter(Context context) {
        this.context = context;
        this.instituicoes = new String[]{"Instituição", "UFC", "IFCE"};
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
        return instituicoes.length;
    }

    @Override
    public Object getItem(int position) {
        return instituicoes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(context, R.layout.instituicoes_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.instituicoes);

        if (position == 0) {
            textView.setTypeface(null, Typeface.ITALIC);
        }

        textView.setText(instituicoes[position]);
        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view;
        view = View.inflate(context, R.layout.instituicoes_dropdown, null);
        TextView textView = (TextView) view.findViewById(R.id.instituicoes_dropdown);

        if (position == 0) {
            textView.setTypeface(null, Typeface.ITALIC);
        }

        textView.setText(instituicoes[position]);
        return view;
    }
}