package com.example.emotrack;

import java.io.Serializable;

public class Sentimento implements Serializable {
    private String nome;
    private String fator;
    private boolean marcante;
    private String observacoes;

    public Sentimento(String nome, String fator, boolean marcante, String observacoes) {
        this.nome = nome;
        this.fator = fator;
        this.marcante = marcante;
        this.observacoes = observacoes;
    }

    public String getNome() {
        return nome;
    }

    public String getFator() {
        return fator;
    }

    public boolean isMarcante() {
        return marcante;
    }

    public String getObservacoes() {
        return observacoes;
    }
}