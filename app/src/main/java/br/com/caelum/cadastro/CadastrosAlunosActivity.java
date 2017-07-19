package br.com.caelum.cadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CadastrosAlunosActivity extends AppCompatActivity {

/*  private String nome ;
    private String telefone;
    private String endereco;
    private String site;
    private Integer nota;
    private Button salvar;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastros_alunos);

        Button salvar = (Button) findViewById(R.id.salvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CadastrosAlunosActivity.this,"Salvando",Toast.LENGTH_SHORT).show();
//                Intent lista = new Intent(CadastrosAlunosActivity.this, ListaAlunosActivity.class);
//                startActivity(lista);
                finish();
            }
        });
    }


}
