package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class CadastrosAlunosActivity extends AppCompatActivity {

    private CadastroHelper cadastro;
    public static final String ALUNO_SELECIONADO = "alunoSelecionado";

    private String localArquivoFoto;
    private static final int REQUEST_CAMERA = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastros_alunos);

        this.cadastro = new CadastroHelper(this);

        Intent intent =  this.getIntent();

        Aluno aluno = (Aluno) intent.getSerializableExtra(ALUNO_SELECIONADO);

        if (aluno != null){
            cadastro.ColocaAlunoNoForm(aluno);
        }
        //Ativ 7.4 pag 118 - Setando clickno button do Cadastro
        Button foto = cadastro.getFotoButton();
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // caminho do arquivo, pegar a referencia do diretório da aplicação  na memória externa
                    // recebe um parametro que define o tipo de media que será criado, este irá criar um subpasta
                //
                 localArquivoFoto = getExternalFilesDir(null) +
                            "/" + System.currentTimeMillis()+".jpg";

                //chamar intent implícita para a camera

                Intent abreCamera = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
                //criar a versão URI para o caminho do local da fot
                Uri localFoto = Uri.fromFile(new File(localArquivoFoto));

                //para esta situação, há uma chave padrão
                abreCamera.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);

                //haverá um retorno(user tirou ou não a foto) e por conta disso, é necessário
                // esse método que irá direto ao OnActivityResult
                startActivityForResult(abreCamera, REQUEST_CAMERA);

            }
        });


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


                //instancia alunoDAO
                AlunoDAO alunoDAO = new AlunoDAO(this);
                //
                Aluno aluno = cadastro.pegaAlunoDoForm();


                if(aluno.getId() != null){
                    //altera no banco
                    alunoDAO.altera(aluno);
                }else {

                    //insere no banco
                    alunoDAO.insere(aluno);

                }

                //confirmação de insert
                Toast.makeText(this, aluno.getNome() + " foi registrado com sucesso!",Toast.LENGTH_SHORT).show();
                //fecha a conexao com a base
                alunoDAO.close();
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


    //Ativ 7.4 pag 118  callback da camera
    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {   // verifica se é o código da camera
        if (requestCode == REQUEST_CAMERA){
            //verifica se o resultado da activity é positivo
            if (resultCode == Activity.RESULT_OK){
                //carrega a imagem
                cadastro.carregarImagem(this.localArquivoFoto);
            } else {
                // é setado como nulo
                this.localArquivoFoto = null;
            }
        }

    }

}
