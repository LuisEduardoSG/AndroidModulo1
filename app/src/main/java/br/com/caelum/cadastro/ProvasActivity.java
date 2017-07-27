package br.com.caelum.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by android6920 on 26/07/17.
 */

public class ProvasActivity extends AppCompatActivity{
    private ListView listaProvas;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        //inserir fragments em activitys    //gerenciador de fragments
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //verficar se é tablet
        if (isTablet()){
            transaction.replace(R.id.prova_lista, new ListaProvasFragment());
            transaction.replace(R.id.prova_detalhes,new DetalhesProvaFragment());
        } else {
            //passa o id da view que queremos carregar o fragment e dps uma nova instancia da classe do frag
            transaction.replace(R.id.provas_view, new ListaProvasFragment());
        }

        //executar na transa Intent intent = new Intent(ProvasActivity)ction
        transaction.commit();

    }

    public boolean isTablet()
    {
       return getResources().getBoolean(R.bool.isTablet);
    }


    public void selecionaProva(Prova provaSelecionada) {
        //recupera instancia de fragment
        FragmentManager manager = getSupportFragmentManager();

        if (isTablet()){
            DetalhesProvaFragment detalhesProva = (DetalhesProvaFragment)
                    manager.findFragmentById(R.id.prova_detalhes);
            detalhesProva.populaCamposComDados(provaSelecionada);
        }
        else
        {
            Bundle argumentos = new Bundle();
            argumentos.putSerializable("prova", provaSelecionada);

            DetalhesProvaFragment detalhesProva = new DetalhesProvaFragment();
            detalhesProva.setArguments(argumentos);

            //inserir fragments em activitys    //iniciaa transação para abrir de fragments
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        //sobre poe a outra fragmnts
            transaction.replace(R.id.provas_view,detalhesProva);
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }
}
