package com.example.emotrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SentimentoAdapter extends RecyclerView.Adapter<SentimentoAdapter.ViewHolder> {

    private final List<Sentimento> lista;
    private OnActionListener listener;

    public interface OnActionListener {
        void onEditar(int position);
        void onExcluir(int position);
    }

    public void setOnActionListener(OnActionListener l) { this.listener = l; }

    public SentimentoAdapter(List<Sentimento> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sentimento, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sentimento sentimento = lista.get(position);

        // Use o contexto para obter as strings
        Context context = holder.itemView.getContext();

        // Combine as strings do resources com os dados do sentimento
        holder.txtSentimento.setText(String.format("%s: %s",
                context.getString(R.string.sentimento),
                sentimento.getNome()));

        holder.txtDataHora.setText(String.format("%s: %s",
                context.getString(R.string.data_hora),
                sentimento.getDataFormatada()));

        holder.txtFator.setText(String.format("%s: %s",
                context.getString(R.string.label_fator),
                sentimento.getFator()));

        holder.txtObs.setText(String.format("%s: %s",
                context.getString(R.string.label_observacoes),
                sentimento.getObservacoes()));

        holder.itemView.setOnLongClickListener(v -> {
            v.showContextMenu();
            return true;
        });

        holder.itemView.setOnCreateContextMenuListener((menu, v, menuInfo) -> {
            MenuInflater inflater = new MenuInflater(v.getContext());
            inflater.inflate(R.menu.menu_contextual_sentimento, menu);

            menu.findItem(R.id.menu_editar).setOnMenuItemClickListener(mi -> {
                if (listener != null) listener.onEditar(holder.getAdapterPosition());
                return true;
            });
            menu.findItem(R.id.menu_excluir).setOnMenuItemClickListener(mi -> {
                if (listener != null) listener.onExcluir(holder.getAdapterPosition());
                return true;
            });
        });

        holder.itemView.setOnLongClickListener(v -> {
            if (longClickListener != null) longClickListener.onItemLongClick(position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtSentimento, txtFator, txtMarcante, txtObs, txtDataHora;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSentimento = itemView.findViewById(R.id.txtSentimento);
            txtFator = itemView.findViewById(R.id.txtFator);
            txtMarcante = itemView.findViewById(R.id.txtMarcante);
            txtObs = itemView.findViewById(R.id.txtObs);
            txtDataHora = itemView.findViewById(R.id.txtDataHora);
        }
    }

    public interface OnItemLongClickListener { void onItemLongClick(int position); }
    private OnItemLongClickListener longClickListener;
    public void setOnItemLongClickListener(OnItemLongClickListener l) { longClickListener = l; }
}
