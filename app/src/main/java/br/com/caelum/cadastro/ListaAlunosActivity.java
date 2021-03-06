package br.com.caelum.cadastro;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.StringDef;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
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
    private static final int REQUEST_LIGACAO = 123;

    private Aluno alunoSelecionado;

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

                edicao.putExtra(ALUNO_SELECIONADO, aluno);


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
            public void onClick(View view) {
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

    public void carregarLista() {

        AlunoDAO dao = new AlunoDAO(this);
        this.alunos = dao.getLista();
        dao.close();


      /*  //pega a instância da lista que está na View
        this.listaAlunos = (ListView) findViewById(R.id.lista_alunos);*/


    //retirado na criação do ListaAlunosAdapter
        // retorna uma view me forma de lista, usando uma collection de data
/*        ArrayAdapter<Aluno> adapter = new
                ArrayAdapter<Aluno>(this,android.R.layout.simple_list_item_1,alunos);*/

        ListaAlunosAdapter adapter = new ListaAlunosAdapter(alunos, this);


        //associa o adapter à lista da activity
        listaAlunos.setAdapter(adapter);

    }

    // exercício 5.3 pag 88
    public void onCreateContextMenu (ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        // final para preservar o endereço na memória
        final Aluno alunoSelec = (Aluno) listaAlunos.getAdapter().getItem(info.position);
                                        // listaAlunos.getItemAtPosition(info.position);
        // adicionao botão no context menu
        MenuItem excluir = menu.add("excluir");

        alunoSelecionado = alunoSelec;

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

        //adicionando novos context menu item, utilizando de intents implicitas
        MenuItem ligar= menu.add("Ligar");

        ligar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                String permissaoLigar = Manifest.permission.CALL_PHONE;
                if (ActivityCompat.checkSelfPermission(ListaAlunosActivity.this, permissaoLigar)
                        == PackageManager.PERMISSION_GRANTED){
                    fazerLigacao();
                }
                else {
                    ActivityCompat.requestPermissions(ListaAlunosActivity.this, new String[]{permissaoLigar}, REQUEST_LIGACAO);
                }
                return true;
            }
        });



        MenuItem sms = menu.add("Enviar SMS");
        Intent enviarSMS = new Intent (Intent.ACTION_VIEW);
        enviarSMS.setData(Uri.parse("sms:"+ alunoSelec.getTelefone()));
        enviarSMS.putExtra("sms_body", "Hello World");
        sms.setIntent(enviarSMS);

        MenuItem mapa=  menu.add("Abrir no mapa");
        Intent abrirMapa = new Intent (Intent.ACTION_VIEW);
        abrirMapa.setData(Uri.parse("geo: 0,0?z=06&q="+ Uri.encode(alunoSelec.getEndereco())));
        mapa.setIntent(abrirMapa);

        MenuItem site= menu.add("Abrir no Navegador");
        Intent abrirSite = new Intent (Intent.ACTION_VIEW);
        abrirSite.setData(Uri.parse("http://"+ alunoSelec.getSite()));
        site.setIntent(abrirSite);


    }



    public void onRequestPermissionResult(int requestCode, String[] permissions, int [] resultados){
        if (requestCode == REQUEST_LIGACAO){
            if(resultados[0] == PackageManager.PERMISSION_GRANTED){
                fazerLigacao();
            } else
            {
                //toast
            }
        }
    }
    @SuppressWarnings({"MissingPermission"})
    private void fazerLigacao() {
        Intent Ligacao = new Intent (Intent.ACTION_CALL);

        Ligacao.setData(Uri.parse("tel:"+ alunoSelecionado.getTelefone()));

        startActivity(Ligacao);


    }



    public boolean onCreateOptionsMenu (Menu menu){
        //classe usada para interpretar xml e montar menus
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista_alunos, menu);
        // se true, mostra o menu no load, caso contrário, Não mostra
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        //verifica qual item que ativou o método
        if (item.getItemId() == R.id.menuSubir){
            // Ex 10.4 Pg 142
            //Cria a instancia do banco e passa o contexto
            //AlunoDAO dao = new AlunoDAO(this);


            //this.alunos = dao.getLista();



           /* String json = new AlunoConverter().toJson(this.alunos);
            WebClient client  = new WebClient();
            String resposta = client.post(json);

            Toast.makeText(this,resposta,Toast.LENGTH_SHORT).show();*/


            // Ex 11.2 Pg 148
            new EnviaAlunosTask(this).execute();
            return  true;
        }
        else if (item.getItemId() == R.id.menuDownload){
            Intent provas = new Intent(this, ProvasActivity.class);
            startActivity(provas);
            return true;

        } else if (item.getItemId() == R.id.menuMap){
            Intent mostrarAlunos = new Intent(this, MostraAlunosAcivity.class);
            startActivity(mostrarAlunos);
            return true;

        } else if (item.getItemId() == R.id.menu_opcoes){
           // OpcoesFragment frag = new OpcoesFragment();
            new OpcoesFragment().show(getSupportFragmentManager(),"");

        }

        return super.onOptionsItemSelected(item);
    }

}
