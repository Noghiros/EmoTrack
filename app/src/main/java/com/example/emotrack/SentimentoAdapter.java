package com.example.emotrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SentimentoAdapter extends BaseAdapter {

    private Context context;
    private String[] nomes;
    private String[] fatores;
    private String[] descricoes;
    private int[] intensidades;

    public SentimentoAdapter(Context context, String[] nomes, String[] fatores, String[] descricoes, int[] intensidades) {
        this.context = context;
        this.nomes = nomes;
        this.fatores = fatores;
        this.descricoes = descricoes;
        this.intensidades = intensidades;
    }

    @Override
    public int getCount() {
        return nomes.length;
    }

    @Override
    public Object getItem(int position) {
        return nomes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_sentimento, parent, false);
        }

        TextView tvNome = convertView.findViewById(R.id.tvNome);
        TextView tvFator = convertView.findViewById(R.id.tvFator);
        TextView tvDescricao = convertView.findViewById(R.id.tvDescricao);
        TextView tvIntensidade = convertView.findViewById(R.id.tvIntensidade);

        tvNome.setText(nomes[position]);
        tvFator.setText("Fator: " + fatores[position]);
        tvDescricao.setText("Descrição: " + descricoes[position]);
        tvIntensidade.setText("Intensidade: " + intensidades[position]);

        return convertView;
    }
}

