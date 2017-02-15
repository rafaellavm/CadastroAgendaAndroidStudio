package com.rafaelamarraschi.agendaalunos.agendaalunos;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Button;
import android.widget.Toast;

import com.rafaelamarraschi.agendaalunos.agendaalunos.DAO.AlunoDAO;
import com.rafaelamarraschi.agendaalunos.agendaalunos.modelo.Aluno;

import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView btnlistaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        //String[] alunos = {"Daniel", "Rafaela", "Elaine", "Manuela"};
        btnlistaAlunos = (ListView) findViewById(R.id.lista_alunos);

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

        registerForContextMenu(btnlistaAlunos);

    }


    private void carregaLista() {
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        btnlistaAlunos.setAdapter(adapter);
    }

    @Override
    //ler sobre ciclo de vida de uma Activity https://developer.android.com/reference/android/app/Activity.html
    //colocndo o método carregaLista aqui ele vai carregar a lista atualizada sempre que adicionarmos um aluno novo, isso por conta do ciclo de vida
    //de uma activity conforme o site
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    //menu de contexto, quando você clica sem tirar o mouse de cima de um aluno aparece a opção 'deletar'
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        //menuinfo diz o iem da lista cliclado

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Aluno aluno = (Aluno) btnlistaAlunos.getItemAtPosition(info.position);

                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                dao.deleta(aluno);
                dao.close();
                carregaLista();

                Toast.makeText(ListaAlunosActivity.this, "Deletar o aluno" + aluno.getNome(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });




    }


}
