package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by android6920 on 26/07/17.
 */

public class ProvasActivity extends AppCompatActivity{

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



        //executar na transaction
        transaction.commit();


    }

    private boolean isTablet()
    {
       return getResources().getBoolean(R.bool.isTablet);
    }


}
