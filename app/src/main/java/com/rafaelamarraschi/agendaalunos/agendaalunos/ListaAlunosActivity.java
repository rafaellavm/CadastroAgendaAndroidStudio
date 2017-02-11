package com.rafaelamarraschi.agendaalunos.agendaalunos;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;

import com.rafaelamarraschi.agendaalunos.agendaalunos.DAO.AlunoDAO;
import com.rafaelamarraschi.agendaalunos.agendaalunos.modelo.Aluno;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

        //String[] alunos = {"Daniel", "Rafaela", "Elaine", "Manuela"};
        ListView btnlistaAlunos = (ListView) findViewById(R.id.lista_alunos);
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        btnlistaAlunos.setAdapter(adapter);


        FloatingActionButton btnNovoAluno = (FloatingActionButton) findViewById(R.id.btnNovoAluno);
        btnNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //trocar de tela
                Intent intentVaiProFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

}
