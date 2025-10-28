package com.example.emotrack;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;


public class Sentimento implements Serializable, Comparable<Sentimento> {

    private String nome;
    private String fator;
    private boolean marcante;
    private String observacoes;
    private long timestamp;

    // 2. Construtor
    public Sentimento(String nome, String fator, boolean marcante, String observacoes, long timestamp) {
        this.nome = nome;
        this.fator = fator;
        this.marcante = marcante;
        this.observacoes = observacoes;
        this.timestamp = timestamp;
    }

    // --- MÉTODOS DE ORDENAÇÃO ---

      // ordem NOME, de A-Z
    @Override
    public int compareTo(Sentimento outro) {
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    // comparação  ordem Z-A
    public static Comparator<Sentimento> COMPARADOR_NOME_DESC = new Comparator<Sentimento>() {
        @Override
        public int compare(Sentimento s1, Sentimento s2) {
            return s2.getNome().compareToIgnoreCase(s1.getNome());
        }
    };

    public static Comparator<Sentimento> COMPARADOR_DATA_DESC = new Comparator<Sentimento>() {
        @Override
        public int compare(Sentimento s1, Sentimento s2) {

            return Long.compare(s2.getTimestamp(), s1.getTimestamp());
        }
    };

    public static Comparator<Sentimento> COMPARADOR_DATA_ASC = new Comparator<Sentimento>() {
        @Override
        public int compare(Sentimento s1, Sentimento s2) {
            return Long.compare(s1.getTimestamp(), s2.getTimestamp());
        }
    };

    // 3. Getters e Setters

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getFator() { return fator; }

    public void setFator(String fator) { this.fator = fator; }
    public boolean isMarcante() { return marcante; }

    public void setMarcante(boolean marcante) { this.marcante = marcante; }
    public String getObservacoes() { return observacoes; }

    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    public long getTimestamp() { return timestamp; }

    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

    // --- MÉTODO DE CONVENIÊNCIA PARA EXIBIÇÃO ---

    public String getDataFormatada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        return sdf.format(new Date(this.timestamp));
    }

}
