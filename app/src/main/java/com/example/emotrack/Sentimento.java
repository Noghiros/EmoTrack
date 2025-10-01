package com.example.emotrack;

import java.io.Serializable;

public class Sentimento implements Serializable {
    private String nome;
    private String fator;
    private boolean marcante;
    private String observacoes;
    private String dataHora;

    public Sentimento() {}

    public Sentimento(String nome, String fator, boolean marcante, String observacoes, String dataHora) {
        this.nome = nome;
        this.fator = fator;
        this.marcante = marcante;
        this.observacoes = observacoes;
        this.dataHora = dataHora;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getFator() { return fator; }
    public void setFator(String fator) { this.fator = fator; }

    public boolean isMarcante() { return marcante; }
    public void setMarcante(boolean marcante) { this.marcante = marcante; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public String getDataHora() { return dataHora; }
    public void setDataHora(String dataHora) { this.dataHora = dataHora; }
}
