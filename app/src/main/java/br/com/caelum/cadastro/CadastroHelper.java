package br.com.caelum.cadastro;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.DateFormat;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

    private ImageView foto;
    private Button fotoButton;

    public boolean nomePreenchido(){
        return !nome.getText().toString().isEmpty();
    }


    public CadastroHelper (CadastrosAlunosActivity activity){

        this.foto = (ImageView) activity.findViewById(R.id.cad_foto);
        this.fotoButton = (Button) activity.findViewById(R.id.cad_foto_button);

        this.nome = (EditText) activity.findViewById(R.id.nome);
        this.telefone = (EditText) activity.findViewById(R.id.telefone);
        this.endereco= (EditText) activity.findViewById(R.id.endereco);
        this.site= (EditText) activity.findViewById(R.id.site);
        this.nota= (RatingBar) activity.findViewById(R.id.nota);

        aluno = new Aluno();
    }


    public Aluno pegaAlunoDoForm(){


        /* pega os dados do aluno da variável global*/
        aluno.setNome(nome.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setSite(site.getText().toString());

        aluno.setNota(Double.valueOf(nota.getRating()));
        //aluno.setNota(Double.valueOf(nota.getProgress()));

        aluno.setCaminhoFoto((String) foto.getTag());


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

    public Button getFotoButton() {
        return fotoButton;
    }

    //Ativ 7.4 pag 118 carregar a imagem nos forms
    public void carregarImagem(String localArquivoFoto) {
        // carrega a foto na memória
        Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivoFoto);
        // criando outro bitmap que carrega a foto de maneira reduzida
        Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap(imagemFoto, imagemFoto.getWidth(), 300, true);

        foto.setImageBitmap(imagemFotoReduzida);
        foto.setTag(localArquivoFoto);
        foto.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}
