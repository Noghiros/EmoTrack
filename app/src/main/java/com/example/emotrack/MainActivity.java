package com.example.emotrack;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emotrack.utilis.UtilisAlert;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SentimentoAdapter adapter;
    private ArrayList<Sentimento> lista;

    // Constantes para SharedPreferences, para evitar erros de digitação
    public static final String ARQUIVO_PREFERENCIAS = "com.example.emotrack.PREFERENCIAS";
    private static final String PREF_TIPO_ORDENACAO = "tipo_ordenacao";
    private static final String PREF_ORDEM_DESC = "ordem_desc";
    private static final String KEY_ORDEM_NOME_ASC = "ordem_nome_asc";
    private static final String KEY_ORDEM_DATA_ASC = "ordem_data_asc";
    private static final String KEY_ULTIMA_ORDENACAO = "ultima_ordenacao"; // "nome" ou "data"

    private boolean ordenacaoNomeAsc = true;
    private boolean ordenacaoDataAsc = true;
    private String ultimaOrdenacao = "data"; // valor padrão


    private final ActivityResultLauncher<Intent> launcher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Sentimento s = (Sentimento) result.getData().getSerializableExtra("sentimento");
                    int pos = result.getData().getIntExtra("position", -1);
                    if (pos >= 0) {
                        lista.set(pos, s);
                    } else {
                        lista.add(s);
                    }
                    // Após adicionar ou editar, reordena a lista conforme a preferência salva
                    ordenarListaComPreferencias();
                }
            });

    private int selectedPosition = -1;
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sentimentos);

        lista = new ArrayList<>();

        adapter = new SentimentoAdapter(lista);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        carregarPreferenciasOrdenacao();
        ordenarLista(); // Aplica a ordenação inicial

        adapter.setOnActionListener(new SentimentoAdapter.OnActionListener() {
            @Override
            public void onEditar(int position) {
                // A lógica de edição foi movida para o clique longo
            }

            @Override
            public void onExcluir(int position) {
                // A lógica de exclusão foi movida para o clique longo
            }
        });

        adapter.setOnItemLongClickListener(position -> {
            if (actionMode != null) return;
            selectedPosition = position;
            actionMode = startActionMode(actionModeCallback);
        });
    }

    private final ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_contextual_sentimento, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (selectedPosition == -1) return false;
            int itemId = item.getItemId();

            if (itemId == R.id.menu_editar) {
                Sentimento S = lista.get(selectedPosition);
                Intent it = new Intent(MainActivity.this, CadastroSentimentoActivity.class);
                it.putExtra("sentimento", S);
                it.putExtra("position", selectedPosition);
                launcher.launch(it);
                mode.finish();
                return true;
            } else if (itemId == R.id.menu_excluir) {
                Sentimento S = lista.get(selectedPosition);
                String mensagem = getString(R.string.confirmacao_exclusao) + S.getNome() + "\"";

                DialogInterface.OnClickListener listenerSim = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        lista.remove(selectedPosition);
                        adapter.notifyItemRemoved(selectedPosition);
                        actionMode.finish();
                    }
                };
                UtilisAlert.confirmarAcao(MainActivity.this, mensagem, listenerSim, null);

            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
            selectedPosition = -1;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_sentimentos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_adicionar) {
            Intent it = new Intent(this, CadastroSentimentoActivity.class);
            launcher.launch(it);
            return true;
        } else if (id == R.id.menu_sobre) {
            startActivity(new Intent(this, SobreActivity.class));
            return true;
        } else if (id == R.id.MenuItemRestaurar) {
            confirmarRestaurarPadroes();

        } else if (id == R.id.menu_ordenar_nome) {
            // Inverte a ordem atual
            ordenacaoNomeAsc = !ordenacaoNomeAsc;
            ultimaOrdenacao = "nome";
            salvarPreferenciasOrdenacao();
            ordenarLista();
            // Atualiza o ícone do menu se necessário
            invalidateOptionsMenu();
            return true;
        } else if (id == R.id.menu_ordenar_data) {
            // Inverte a ordem atual
            ordenacaoDataAsc = !ordenacaoDataAsc;
            ultimaOrdenacao = "data";
            salvarPreferenciasOrdenacao();
            ordenarLista();
            // Atualiza o ícone do menu se necessário
            invalidateOptionsMenu();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvarPreferenciaOrdenacao(String tipo, boolean descendente) {
        SharedPreferences prefs = getSharedPreferences(ARQUIVO_PREFERENCIAS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREF_TIPO_ORDENACAO, tipo);
        editor.putBoolean(PREF_ORDEM_DESC, descendente);
        editor.apply();
    }

    private void salvarPreferenciasOrdenacao() {
        SharedPreferences prefs = getSharedPreferences(ARQUIVO_PREFERENCIAS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_ORDEM_NOME_ASC, ordenacaoNomeAsc);
        editor.putBoolean(KEY_ORDEM_DATA_ASC, ordenacaoDataAsc);
        editor.putString(KEY_ULTIMA_ORDENACAO, ultimaOrdenacao);
        editor.apply();
    }

    private void carregarPreferenciasOrdenacao() {
        SharedPreferences prefs = getSharedPreferences(ARQUIVO_PREFERENCIAS, MODE_PRIVATE);
        ordenacaoNomeAsc = prefs.getBoolean(KEY_ORDEM_NOME_ASC, true);
        ordenacaoDataAsc = prefs.getBoolean(KEY_ORDEM_DATA_ASC, true);
        ultimaOrdenacao = prefs.getString(KEY_ULTIMA_ORDENACAO, "data");
    }

    private void ordenarListaComPreferencias() {
        SharedPreferences prefs = getSharedPreferences(ARQUIVO_PREFERENCIAS, MODE_PRIVATE);
        String tipo = prefs.getString(PREF_TIPO_ORDENACAO, "data");
        boolean descendente = prefs.getBoolean(PREF_ORDEM_DESC, true);

        ordenarLista(tipo, descendente);
    }

    public void ordenarLista(String tipo, boolean descendente) {
        if (tipo.equals("nome")) {
            if (descendente) { // Z -> A
                Collections.sort(lista, Sentimento.COMPARADOR_NOME_DESC);
            } else { // A -> Z
                Collections.sort(lista); // Usa a ordem natural (compareTo) que definimos como A-Z
            }
        } else if (tipo.equals("data")) {
            if (descendente) { // Mais recente primeiro
                Collections.sort(lista, Sentimento.COMPARADOR_DATA_DESC);
            } else { // Mais antigo primeiro
                Collections.sort(lista, Sentimento.COMPARADOR_DATA_ASC);
            }
        }

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    private void ordenarLista() {
        if (lista == null || lista.isEmpty()) return;

        if ("data".equals(ultimaOrdenacao)) {
            // Ordena por data
            Collections.sort(lista, (s1, s2) -> {
                int comparacao = s1.getDataFormatada().compareTo(s2.getDataFormatada());
                return ordenacaoDataAsc ? comparacao : -comparacao;
            });
        } else {
            // Ordena por nome
            Collections.sort(lista, (s1, s2) -> {
                int comparacao = s1.getNome().compareToIgnoreCase(s2.getNome());
                return ordenacaoNomeAsc ? comparacao : -comparacao;
            });
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem menuNome = menu.findItem(R.id.menu_ordenar_nome);
        MenuItem menuData = menu.findItem(R.id.menu_ordenar_data);

        if ("nome".equals(ultimaOrdenacao)) {
            menuNome.setIcon(ordenacaoNomeAsc ?
                    R.drawable.ic_action_name_ascending_order :
                    R.drawable.ic_action_name_descending_order);
        }

        if ("data".equals(ultimaOrdenacao)) {
            menuData.setIcon(ordenacaoDataAsc ?
                    R.drawable.ic_action_name_ascending_order :
                    R.drawable.ic_action_name_descending_order);
        }

        return true;
    }

    private void confirmarRestaurarPadroes() {

        DialogInterface.OnClickListener listenerSim = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences shared = getSharedPreferences(ARQUIVO_PREFERENCIAS, MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();

                editor.clear();
                editor.commit();
                ordenacaoNomeAsc = true;

                Toast.makeText(MainActivity.this, R.string.msg_config_padrao, Toast.LENGTH_SHORT).show();

            }
        };
        UtilisAlert.confirmarAcao(MainActivity.this, getString(R.string.deseja_voltar_padroes),listenerSim,null);


    }
}
