package br.com.caelum.cadastro;

import android.icu.text.DateFormat;
import android.widget.EditText;
import android.widget.RatingBar;

/**
 * Created by android6920 on 19/07/17.
 */
public class CadastroHelper {

    private Aluno aluno;

    private EditText nome ;
    private EditText telefone;
    private EditText endereco;
    private EditText site;
    private RatingBar nota;

    public boolean nomePreenchido(){
        return !nome.getText().toString().isEmpty();
    }


    public CadastroHelper (CadastrosAlunosActivity activity){

        this.nome = (EditText) activity.findViewById(R.id.nome);
        this.telefone = (EditText) activity.findViewById(R.id.telefone);
        this.endereco= (EditText) activity.findViewById(R.id.endereco);
        this.site= (EditText) activity.findViewById(R.id.site);
        this.nota= (RatingBar) activity.findViewById(R.id.nota);

        aluno = new Aluno();
    }


    public Aluno getAluno(){


        /* pega os dados do aluno da variável global*/
        aluno.setNome(nome.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setSite(site.getText().toString());

        aluno.setNota(Double.valueOf(nota.getRating()));
        //aluno.setNota(Double.valueOf(nota.getProgress()));

        return aluno;
    }

    public void mostrarErro() {
        nome.setError("Campo nome não pode ser vazio, favor preencher");
    }

    public void ColocaAlunoNoForm(Aluno aluno) {


        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
        endereco.setText(aluno.getEndereco());
        site.setText(aluno.getSite());
        nota.setRating(aluno.getNota().intValue());

        this.aluno = aluno;
    }
}
