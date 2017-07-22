package br.com.caelum.cadastro;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listaAlunos;
    private List<Aluno> alunos;

    public static final String ALUNO_SELECIONADO = "alunoSelecionado";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // executa os métodos que contém no onCreate de origem
        super.onCreate(savedInstanceState);

        //associa a view com a class
        setContentView(R.layout.activity_lista_alunos);

        AlunoDAO dao = new AlunoDAO(this);
        alunos = dao.getLista();
        dao.close();


        //pega a instância da lista que está na View
        this.listaAlunos = (ListView) findViewById(R.id.lista_alunos);

       /*
        // retorna uma view me forma de lista, usando uma collection de data
        ArrayAdapter<Aluno> adapter = new
                ArrayAdapter<Aluno>(this,android.R.layout.simple_list_item_1,alunos);


        //associa o adapter à lista da activity
        listaAlunos.setAdapter(adapter);
        */


        //Exercício 5.3 pag 88
        registerForContextMenu(listaAlunos);


        // associa um item click listener (click rapido) aos itens da lista
        /*listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {
                Toast.makeText(ListaAlunosActivity.this, "Posição selecionada " + posicao, Toast.LENGTH_SHORT).show();
            }
        });*/

        //Enviar o Aluno selecionado no clique no item
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {

                Intent edicao = new Intent(ListaAlunosActivity.this, CadastrosAlunosActivity.class);

                Aluno aluno = (Aluno) adapterView.getItemAtPosition(pos);

                edicao.putExtra(ALUNO_SELECIONADO,  aluno);


                startActivity(edicao);



            }
        });



        // associa um item click long listener (click long) aos itens da lista
       /* listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long l) {
                String aluno = (String) adapterView.getItemAtPosition(posicao);
                Toast.makeText(ListaAlunosActivity.this, "Click Longo: " + aluno, Toast.LENGTH_SHORT).show();
                return false;
            }
        });*/

        //instancia do botão add aluno, o floating
        Button addAluno = (Button) findViewById(R.id.addAluno);

        //seta o clicklistener
        addAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                // instancia uma intenção, passando o contexto e activity que está em foco
                 Intent intent = new Intent(ListaAlunosActivity.this, CadastrosAlunosActivity.class);
                //da start na intenção
                 startActivity(intent);
            }
        });


    }

    protected void onResume(){
        super.onResume();
        this.carregarLista();

    }

    private void carregarLista() {

        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.getLista();
        dao.close();


      /*  //pega a instância da lista que está na View
        this.listaAlunos = (ListView) findViewById(R.id.lista_alunos);*/

        // retorna uma view me forma de lista, usando uma collection de data
        ArrayAdapter<Aluno> adapter = new
                ArrayAdapter<Aluno>(this,android.R.layout.simple_list_item_1,alunos);


        //associa o adapter à lista da activity
        listaAlunos.setAdapter(adapter);

    }

    // exercício 5.3 pag 88
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)
                menuInfo;

        //final para preservar o endereço na memória
        final Aluno alunoSelec = (Aluno) listaAlunos.getAdapter().getItem(info.position);
                                        //listaAlunos.getItemAtPosition(info.position);
        // adicionao botão no context menu
        MenuItem excluir = menu.add("excluir");

        //cria o listener desse botao
        excluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                //instancia o dao
                AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
                //executa o delete
                dao.deletar(alunoSelec);
                //fecha o banco
                dao.close();
                //carrega a lista dnv
                carregarLista();

                return true;
            }
        });


    }


}
