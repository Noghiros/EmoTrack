package com.example.emotrack;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);

        TextView tvTitulo = findViewById(R.id.tvTitulo);
        TextView tvAluno = findViewById(R.id.tvAluno);
        TextView tvCurso = findViewById(R.id.tvCurso);
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvDescricao = findViewById(R.id.tvDescricao);
        ImageView imgLogo = findViewById(R.id.imgLogo);

        tvTitulo.setText("EmoTrack");
        tvAluno.setText("Aluno: Stefano Calheiros Stringhini");
        tvCurso.setText("Curso: Engenharia de Computação");
        tvEmail.setText("E-mail: stefanoc.stringhini@alunos.utfpr.edu.br");
        tvDescricao.setText("EmoTrack ajuda a registrar sentimentos, associar fatores e acompanhar seu histórico pessoal.");
        imgLogo.setImageResource(R.drawable.logo_utfpr);
    }
}