package com.example.emotrack;

import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        Sentimento s = lista.get(position);
        holder.txtSentimento.setText("Sentimento: " + s.getNome());
        holder.txtFator.setText("Situação: " + s.getFator());
        holder.txtMarcante.setText(s.isMarcante() ? "Marcante" : "");
        holder.txtObs.setText("Observação: " + s.getObservacoes());
        holder.txtDataHora.setText("Registrado em: " + s.getDataHora());

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
