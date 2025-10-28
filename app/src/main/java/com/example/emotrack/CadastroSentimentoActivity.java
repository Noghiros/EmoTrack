package com.example.emotrack;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class CadastroSentimentoActivity extends AppCompatActivity {

    private RadioGroup rgSentimento;
    private Spinner spFator;
    private CheckBox cbMarcante;
    private EditText etObservacoes;
    private int editPosition = -1;

    private boolean sugerirTipo = false;
    private int ultimoTipo = 0;
    private static final String KEY_SUGERIR_TIPO = "sugerirTipo";
    private static final String KEY_ULTIMO_TIPO = "ultimoTipo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_sentimento);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rgSentimento = findViewById(R.id.rgSentimento);
        spFator = findViewById(R.id.spFator);
        cbMarcante = findViewById(R.id.cbMarcante);
        etObservacoes = findViewById(R.id.etObservacoes);

        lerPreferencias();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_fatores, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFator.setAdapter(adapter);


        // Tenta pegar um objeto "Sentimento" que foi passado via Intent.
        Sentimento s = (Sentimento) getIntent().getSerializableExtra("sentimento");

        // Tenta pegar a "posição" do item na lista que foi passada via Intent.
        editPosition = getIntent().getIntExtra("position", -1);

        if (sugerirTipo) {
            spFator.setSelection(ultimoTipo);

        }

        // SE um sentimento (s) foi recebido, entramos em MODO EDIÇÃO.
        if (s != null) {
            for (int i = 0; i < rgSentimento.getChildCount(); i++) {
                if (rgSentimento.getChildAt(i) instanceof RadioButton) {
                    RadioButton rb = (RadioButton) rgSentimento.getChildAt(i);
                    if (rb.getText().toString().equals(s.getNome())) {
                        rb.setChecked(true);
                        break;
                    }
                }
            }
            int pos = adapter.getPosition(s.getFator());
            if (pos >= 0) spFator.setSelection(pos);
            cbMarcante.setChecked(s.isMarcante());
            etObservacoes.setText(s.getObservacoes());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sentimento_opcoes, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
    MenuItem item = menu.findItem(R.id.menuItemSugerirTipo);
    item.setChecked(sugerirTipo);
    return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setResult(RESULT_CANCELED);
            finish();
            return true;
        }
        int id = item.getItemId();
        if (id == R.id.menu_salvar) {
            int sel = rgSentimento.getCheckedRadioButtonId();
            if (sel == -1) {
                Toast.makeText(this, R.string.selecione_sentimento, Toast.LENGTH_SHORT).show();
                return true;
            }
            RadioButton rb = findViewById(sel);
            String nome = rb.getText().toString();
            String fator = spFator.getSelectedItem().toString();
            boolean marc = cbMarcante.isChecked();
            String obs = etObservacoes.getText().toString().trim();

            int tipoFator = spFator.getSelectedItemPosition();
            salvarUltimoTipo(tipoFator);

            if (obs.isEmpty()) {
                Toast.makeText(this, R.string.preencha_obs, Toast.LENGTH_SHORT).show();
                return true;
            }
            long dataHora = new Date().getTime();
            Sentimento novo = new Sentimento(nome, fator, marc, obs, dataHora);
            Intent res = new Intent();
            res.putExtra("sentimento", novo);
            if (editPosition >= 0) res.putExtra("position", editPosition);
            setResult(RESULT_OK, res);
            finish();
            return true;
        } else if (id == R.id.menu_limpar) {
            rgSentimento.clearCheck();
            spFator.setSelection(0);
            cbMarcante.setChecked(false);
            etObservacoes.setText("");
            Toast.makeText(this, R.string.form_limpo, Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menuItemSugerirTipo){
            boolean valor = !item.isChecked();
            salvarSugerirTipo(valor);
            item.setChecked(valor);

            if(sugerirTipo) {
                spFator.setSelection(ultimoTipo);
            }

            return true;
        }else if (id == R.id.menu_editar) {
            Toast.makeText(this, "Editar selecionado", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.menu_excluir) {
            Toast.makeText(this, "Excluir selecionado", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void lerPreferencias(){

        SharedPreferences shared = getSharedPreferences(MainActivity.ARQUIVO_PREFERENCIAS, Context.MODE_PRIVATE);

        sugerirTipo = shared.getBoolean(KEY_SUGERIR_TIPO, sugerirTipo);
        ultimoTipo  = shared.getInt(KEY_ULTIMO_TIPO, ultimoTipo);
    }

    private void salvarSugerirTipo(boolean novoValor){

        SharedPreferences shared = getSharedPreferences(MainActivity.ARQUIVO_PREFERENCIAS, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = shared.edit();

        editor.putBoolean(KEY_SUGERIR_TIPO, novoValor);

        editor.commit();

        sugerirTipo = novoValor;
    }

    private void salvarUltimoTipo(int novoValor){

        SharedPreferences shared = getSharedPreferences(MainActivity.ARQUIVO_PREFERENCIAS, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = shared.edit();

        editor.putInt(KEY_ULTIMO_TIPO, novoValor);

        editor.commit();

        ultimoTipo = novoValor;
    }

}
