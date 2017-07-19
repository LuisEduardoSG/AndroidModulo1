package br.com.caelum.cadastro;

import android.content.Intent;
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        String[] alunos = {"Anderson","Filipe","Guilherme"};
        this.listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        ArrayAdapter<String> adapter = new
                    ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,alunos);
        listaAlunos.setAdapter(adapter);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long l) {
                Toast.makeText(ListaAlunosActivity.this, "Posição selecionada " + posicao, Toast.LENGTH_SHORT).show();
            }

        });

        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long l) {
                String aluno = (String) adapterView.getItemAtPosition(posicao);
                Toast.makeText(ListaAlunosActivity.this, "Click Longo: " + aluno, Toast.LENGTH_SHORT).show();
                return true;
            }
        });


        Button addAluno = (Button) findViewById(R.id.addAluno);
        addAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
//                finish();
                 Intent intent = new Intent(ListaAlunosActivity.this, CadastrosAlunosActivity.class);
                 startActivity(intent);
            }
        });



    }
}
