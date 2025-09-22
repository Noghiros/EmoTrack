package com.example.emotrack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroSentimentoActivity extends AppCompatActivity {

    private EditText edtNome, edtFator, edtObs;
    private CheckBox chkMarcante;
    private Button btnSalvar, btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_sentimento);

        edtNome = findViewById(R.id.edtNome);
        edtFator = findViewById(R.id.edtFator);
        edtObs = findViewById(R.id.edtObs);
        chkMarcante = findViewById(R.id.chkMarcante);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnCancelar = findViewById(R.id.btnCancelar);

        btnSalvar.setOnClickListener(v -> {
            String nome = edtNome.getText().toString();
            String fator = edtFator.getText().toString();
            String obs = edtObs.getText().toString();
            boolean marcante = chkMarcante.isChecked();

            if (nome.isEmpty() || fator.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos obrigatórios.", Toast.LENGTH_SHORT).show();
                return;
            }

            Sentimento s = new Sentimento(nome, fator, marcante, obs);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("sentimento", s);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        btnCancelar.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });
    }
}