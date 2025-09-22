package com.example.emotrack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SentimentoAdapter adapter;
    private ArrayList<Sentimento> listaSentimentos;

    private Button btnAdicionar, btnSobre;

    // Launcher para abrir CadastroSentimentoActivity e receber o resultado
    private final ActivityResultLauncher<Intent> launcherCadastro =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Sentimento novo = (Sentimento) result.getData().getSerializableExtra("sentimento");
                            if (novo != null) {
                                listaSentimentos.add(novo);
                                adapter.notifyItemInserted(listaSentimentos.size() - 1);
                            }
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sentimentos);

        // Inicializa lista e adapter
        listaSentimentos = new ArrayList<>();
        adapter = new SentimentoAdapter(listaSentimentos);

        // Configura RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Botões
        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnSobre = findViewById(R.id.btnSobre);

        // Ações
        btnAdicionar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CadastroSentimentoActivity.class);
            launcherCadastro.launch(intent);
        });

        btnSobre.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SobreActivity.class);
            startActivity(intent);
        });
    }
}
