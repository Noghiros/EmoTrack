package com.example.emotrack;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rgSentimento;
    private Spinner spFator;
    private CheckBox cbMarcante;
    private EditText etObservacoes;
    private Button btnSalvar, btnLimpar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgSentimento = findViewById(R.id.rgSentimento);
        spFator = findViewById(R.id.spFator);
        cbMarcante = findViewById(R.id.cbMarcante);
        etObservacoes = findViewById(R.id.etObservacoes);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnLimpar = findViewById(R.id.btnLimpar);

        // Adapter do Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.array_fatores,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFator.setAdapter(adapter);

        btnLimpar.setOnClickListener(v -> limparFormulario());

        btnSalvar.setOnClickListener(v -> salvarRegistro());
    }

    private void limparFormulario() {
        rgSentimento.clearCheck();
        spFator.setSelection(0); // "Selecione…"
        cbMarcante.setChecked(false);
        etObservacoes.setText("");
        etObservacoes.clearFocus();

        Toast.makeText(this, getString(R.string.toast_limpo), Toast.LENGTH_SHORT).show();
    }

    private void salvarRegistro() {
        // RadioGroup
        int checkedId = rgSentimento.getCheckedRadioButtonId();
        if (checkedId == -1) {
            Toast.makeText(this, getString(R.string.toast_erro_radio), Toast.LENGTH_SHORT).show();
            // retorna o foco lógico ao grupo (foco de acessibilidade)
            rgSentimento.requestFocus();
            return;
        }

        // EditText
        String obs = etObservacoes.getText().toString().trim();
        if (obs.isEmpty()) {
            Toast.makeText(this, getString(R.string.toast_erro_obs), Toast.LENGTH_SHORT).show();
            etObservacoes.requestFocus();
            etObservacoes.setError(getString(R.string.toast_erro_obs));
            return;
        }

        // Coleta de valores
        RadioButton rbSelecionado = findViewById(checkedId);
        String sentimento = rbSelecionado.getText().toString();

        String fator = spFator.getSelectedItem() != null
                ? spFator.getSelectedItem().toString()
                : "";

        boolean marcante = cbMarcante.isChecked();

        // Toast curto
        String mensagem = getString(R.string.toast_salvo) + " | " + sentimento + " | " + fator + " | " + (marcante ? "Marcante" : "Comum");

        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();

    }
}
