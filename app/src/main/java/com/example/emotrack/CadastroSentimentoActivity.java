package com.example.emotrack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroSentimentoActivity extends AppCompatActivity {

    private RadioGroup rgSentimento;
    private Spinner spFator;
    private CheckBox cbMarcante;
    private EditText etObservacoes;
    private Button btnLimpar, btnSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_sentimento);

        rgSentimento = findViewById(R.id.rgSentimento);
        spFator = findViewById(R.id.spFator);
        cbMarcante = findViewById(R.id.cbMarcante);
        etObservacoes = findViewById(R.id.etObservacoes);
        btnLimpar = findViewById(R.id.btnLimpar);
        btnSalvar = findViewById(R.id.btnSalvar);

        // Exemplo de preenchimento do Spinner
        ArrayAdapter<CharSequence> adapterFator = ArrayAdapter.createFromResource(
                this, R.array.sentimentos_fatores, android.R.layout.simple_spinner_item);
        adapterFator.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFator.setAdapter(adapterFator);

        btnSalvar.setOnClickListener(v -> {
            int selectedId = rgSentimento.getCheckedRadioButtonId();
            if (selectedId == -1) {
                Toast.makeText(this, "Selecione um sentimento!", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton rbSelecionado = findViewById(selectedId);
            String nome = rbSelecionado.getText().toString();
            String fator = spFator.getSelectedItem().toString();
            boolean marcante = cbMarcante.isChecked();
            String obs = etObservacoes.getText().toString().trim();

            if (obs.isEmpty()) {
                Toast.makeText(this, "Preencha as observações!", Toast.LENGTH_SHORT).show();
                return;
            }

            Sentimento s = new Sentimento(nome, fator, marcante, obs);

            Intent resultIntent = new Intent();
            resultIntent.putExtra("sentimento", s);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        btnLimpar.setOnClickListener(v -> {
            rgSentimento.clearCheck();
            spFator.setSelection(0);
            cbMarcante.setChecked(false);
            etObservacoes.setText("");
            Toast.makeText(this, "Formulário limpo!", Toast.LENGTH_SHORT).show();
        });
    }
}
