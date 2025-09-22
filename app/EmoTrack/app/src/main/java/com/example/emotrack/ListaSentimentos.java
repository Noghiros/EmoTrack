package com.example.emotrack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaSentimentos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SentimentoAdapter adapter;
    private ArrayList<Sentimento> lista;

    private static final int REQUEST_CODE_CADASTRO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sentimentos);

        recyclerView = findViewById(R.id.recyclerView);
        Button btnAdicionar = findViewById(R.id.btnAdicionar);
        Button btnSobre = findViewById(R.id.btnSobre);

        lista = new ArrayList<>();
        adapter = new SentimentoAdapter(lista);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Botão de cadastro
        btnAdicionar.setOnClickListener(v -> {
            Intent intent = new Intent(this, CadastroSentimentoActivity.class);
            startActivityForResult(intent, REQUEST_CODE_CADASTRO);
        });

        // Botão sobre
        btnSobre.setOnClickListener(v -> {
            Intent intent = new Intent(this, SobreActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CADASTRO && resultCode == RESULT_OK && data != null) {
            String nome = data.getStringExtra("nome");
            String fator = data.getStringExtra("fator");
            boolean marcante = data.getBooleanExtra("marcante", false);
            String observacoes = data.getStringExtra("observacoes");

            Sentimento novo = new Sentimento(nome, fator, marcante, observacoes);
            lista.add(novo);
            adapter.notifyItemInserted(lista.size() - 1);
        }
    }
}