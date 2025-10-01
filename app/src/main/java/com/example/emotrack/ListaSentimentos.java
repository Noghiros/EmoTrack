package com.example.emotrack;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaSentimentos extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SentimentoAdapter adapter;
    private ArrayList<Sentimento> lista;

    private final ActivityResultLauncher<Intent> launcherNovoCadastro =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                            Sentimento novo = (Sentimento) result.getData().getSerializableExtra("sentimento");
                            if (novo != null) {
                                lista.add(novo);
                                adapter.notifyItemInserted(lista.size() - 1);
                            }
                        }
                    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sentimentos);

        lista = new ArrayList<>();
        adapter = new SentimentoAdapter(lista);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    // método auxiliar para abrir cadastro (opcional)
    public void abrirCadastroSentimento() {
        Intent intent = new Intent(this, CadastroSentimentoActivity.class);
        launcherNovoCadastro.launch(intent);
    }
}
