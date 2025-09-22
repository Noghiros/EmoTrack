package com.example.emotrack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnAdicionar, btnSobre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnSobre = findViewById(R.id.btnSobre);

        btnAdicionar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListaSentimentos.class);
            startActivity(intent);
        });

        btnSobre.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SobreActivity.class);
            startActivity(intent);
        });
    }
}