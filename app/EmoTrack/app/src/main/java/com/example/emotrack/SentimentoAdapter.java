package com.example.emotrack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SentimentoAdapter extends RecyclerView.Adapter<SentimentoAdapter.SentimentoViewHolder> {

    private ArrayList<Sentimento> lista;

    public SentimentoAdapter(ArrayList<Sentimento> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public SentimentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sentimento, parent, false);
        return new SentimentoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SentimentoViewHolder holder, int position) {
        Sentimento sentimento = lista.get(position);

        holder.txtTitulo.setText("Sentimento: " + sentimento.getNome());
        holder.txtDescricao.setText("Fator: " + sentimento.getFator());
        holder.txtMarcante.setText(sentimento.isMarcante() ? "Marcante" : "Comum");
        holder.txtObs.setText("Obs: " + sentimento.getObservacoes());

        holder.itemView.setOnClickListener(v -> {
            android.widget.Toast.makeText(
                    v.getContext(),
                    "Clicou em: " + sentimento.getNome(),
                    android.widget.Toast.LENGTH_SHORT
            ).show();
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public static class SentimentoViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo, txtDescricao, txtMarcante, txtObs;

        public SentimentoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtDescricao = itemView.findViewById(R.id.txtDescricao);
            txtMarcante = itemView.findViewById(R.id.txtMarcante);
            txtObs = itemView.findViewById(R.id.txtObs);
        }
    }
}