package br.com.caelum.cadastro;

import android.content.Intent;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CadastrosAlunosActivity extends AppCompatActivity {

    private CadastroHelper cadastro;
    private AlunoDAO alunoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastros_alunos);

        this.cadastro = new CadastroHelper(this);

      /*  //pega a instância do botão da view
        Button salvar = (Button) findViewById(R.id.salvar);
        //dar um set listener no click do botão salvar
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mensagem ao usuário, passando o contexto, a mensagem e a duração. .show para mostrar a msg
                Toast.makeText(CadastrosAlunosActivity.this,"Salvando",Toast.LENGTH_SHORT).show();
                //fecha a activity atual
                finish();
            }
        });*/


    }

    public boolean onCreateOptionsMenu (Menu menu){
        //classe usada para interpretar xml e montar menus
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro, menu);
        // se true, mostra o menu no load, caso contrário, Não mostra
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        //verifica qual item que ativou o método
        if (item.getItemId() == R.id.menuSalvar){

            //executa código associado ao botão
            if (cadastro.nomePreenchido()){
                //
                Aluno aluno = cadastro.getAluno();


                //instancia alunoDAO
                alunoDAO = new AlunoDAO(this);
                //insere no banco
                alunoDAO.insere(aluno);

                Toast.makeText(this, aluno.getNome() + " foi registrado com sucesso!",Toast.LENGTH_SHORT).show();

                //fecha a activity
                finish();

                //return true caso não é pra executar mais nada
                return false;
            }else {
                cadastro.mostrarErro();
            }


        }
        //return false caso há outras funções para serem executads
        return false;
    }

}