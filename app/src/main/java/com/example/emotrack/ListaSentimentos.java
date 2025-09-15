package com.example.emotrack;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ListaSentimentos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sentimentos);

        // Carregar os arrays do strings.xml
        String[] nomes = getResources().getStringArray(R.array.sentimentos_nomes);
        String[] fatores = getResources().getStringArray(R.array.sentimentos_fatores);
        String[] descricoes = getResources().getStringArray(R.array.sentimentos_descricoes);
        int[] intensidades = getResources().getIntArray(R.array.sentimentos_intensidades);

        // ListView
        ListView listView = findViewById(R.id.listViewSentimentos);

        // Adapter personalizado
        SentimentoAdapter adapter = new SentimentoAdapter(this, nomes, fatores, descricoes, intensidades);
        listView.setAdapter(adapter);

        // Clique em um item → mostrar Toast
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String msg = "Você clicou em: " + nomes[position] +
                        " | Fator: " + fatores[position] +
                        " | Intensidade: " + intensidades[position];
                Toast.makeText(ListaSentimentos.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}