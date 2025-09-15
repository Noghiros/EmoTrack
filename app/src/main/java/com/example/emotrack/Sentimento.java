package com.example.emotrack;

public class Sentimento {
    private String nome;        // Ex: "Feliz"
    private String fator;       // Ex: "Trabalho", "Família"
    private String descricao;   // Uma frase sobre o sentimento
    private int intensidade;    // Ex: nível de 1 a 10

    public Sentimento(String nome, String fator, String descricao, int intensidade) {
        this.nome = nome;
        this.fator = fator;
        this.descricao = descricao;
        this.intensidade = intensidade;
    }

    // Getters
    public String getNome() { return nome; }
    public String getFator() { return fator; }
    public String getDescricao() { return descricao; }
    public int getIntensidade() { return intensidade; }
}

