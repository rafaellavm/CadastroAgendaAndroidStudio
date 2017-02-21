package com.rafaelamarraschi.agendaalunos.agendaalunos;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.rafaelamarraschi.agendaalunos.agendaalunos.DAO.AlunoDAO;
import com.rafaelamarraschi.agendaalunos.agendaalunos.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    //pra ser usado na classe toda
    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);

        //CASO SEJA ALTERACAO DO ALUNO, a 'etiqueta' 'aluno' virá preenchida, senão virá vazia, que no caso virará adição de aluno
        //recuperar a intent que foi usada pra abrir esse formulario (no caso o clique no aluno da lista)
        Intent intent = getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if (aluno != null) {
            helper.preencheFormulario(aluno);
        }
    }

    // --------------------------------------- criacao do optionsMenu e atribuicao de qual menu fará parte dele   ---------------------------------------
    //método que cria e especifica quais itens do menu serão colocados na parte de cima
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // --------------------------------------- botão menu para adicionar aluno  -----------------------------------------------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //ele vai buscar todos os menus, então tem que especificar
        switch (item.getItemId()){

            //se for o botão 'ok' do menu superior, menu xml criado na pasta /menu
            case R.id.menu_formulario_ok:

                Aluno aluno = helper.pegaAluno();
                AlunoDAO dao = new AlunoDAO(this);
                dao.insere(aluno);
                dao.close();

                Toast.makeText(FormularioActivity.this,"Aluno " + aluno.getNome() + " salvo", Toast.LENGTH_SHORT).show();

                //finalizar a activity da lista
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
