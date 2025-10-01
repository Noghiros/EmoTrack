package com.example.emotrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SentimentoAdapter adapter;
    private ArrayList<Sentimento> lista;

    private final ActivityResultLauncher<Intent> launcher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Sentimento s = (Sentimento) result.getData().getSerializableExtra("sentimento");
                    int pos = result.getData().getIntExtra("position", -1);
                    if (pos >= 0) {
                        lista.set(pos, s);
                        adapter.notifyItemChanged(pos);
                    } else {
                        lista.add(s);
                        adapter.notifyItemInserted(lista.size() - 1);
                    }
                }
            });

    private int selectedPosition = -1;
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_sentimentos);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lista = new ArrayList<>();
        adapter = new SentimentoAdapter(lista);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnActionListener(new SentimentoAdapter.OnActionListener() {
            @Override
            public void onEditar(int position) {
            }
            @Override
            public void onExcluir(int position) {
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
        public boolean onCreateActionMode(ActionMode mode, android.view.Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu_contextual_sentimento, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, android.view.Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, android.view.MenuItem item) {
            if (selectedPosition == -1) return false;
            if (item.getItemId() == R.id.menu_editar) {
                Sentimento s = lista.get(selectedPosition);
                Intent it = new Intent(MainActivity.this, CadastroSentimentoActivity.class);
                it.putExtra("sentimento", s);
                it.putExtra("position", selectedPosition);
                launcher.launch(it);
                mode.finish();
                return true;
            } else if (item.getItemId() == R.id.menu_excluir) {
                lista.remove(selectedPosition);
                adapter.notifyItemRemoved(selectedPosition);
                adapter.notifyItemRangeChanged(selectedPosition, lista.size());
                mode.finish();
                return true;
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
        } else if (id == R.id.menu_ordenar) {
            Collections.sort(lista, (a, b) -> a.getNome().compareToIgnoreCase(b.getNome()));
            adapter.notifyDataSetChanged();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            setResult(RESULT_CANCELED);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
