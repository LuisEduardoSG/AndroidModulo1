package br.com.caelum.cadastro;

import android.content.Intent;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listaAlunos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // executa os métodos que contém no onCreate de origem
        super.onCreate(savedInstanceState);

        //associa a view com a class
        setContentView(R.layout.activity_lista_alunos);

        // array de dados para  lista
        String[] alunos = {"Android","Caelum","Modulo 1", "Git Hub"};

        //pega a instância da lista que está na View
        this.listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        // retorna uma view me forma de lista, usando uma collection de data
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,alunos);

        //ArrayAdapter<String> adapter = new
         //       ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alunos);
        //associa o adapter à lista da activity
        listaAlunos.setAdapter(adapter);

        // associa um item click listener (click rapido) aos itens da lista
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {
                Toast.makeText(ListaAlunosActivity.this, "Posição selecionada " + posicao, Toast.LENGTH_SHORT).show();
            }

        });

        // associa um item click long listener (click long) aos itens da lista
        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long l) {
                String aluno = (String) adapterView.getItemAtPosition(posicao);
                Toast.makeText(ListaAlunosActivity.this, "Click Longo: " + aluno, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

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
}
