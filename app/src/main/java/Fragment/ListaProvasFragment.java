package Fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.caelum.cadastro.Prova;
import br.com.caelum.cadastro.R;

/**
 * Created by android6920 on 26/07/17.
 */
//Ex 13.3 Pag 160
public class ListaProvasFragment extends Fragment {

    private ListView listViewProvas;


    @Override
    public Context getContext() {
        return super.getContext();
    }
                            //inflater da classe                        //girar a tela
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle bundle){
                                                                            //se a
        View view = inflater.inflate(R.layout.fragment_lista_provas,  parent,true);

        listViewProvas =  (ListView) view.findViewById(R.id.lista_provas);

        List<Prova> provas = new ArrayList<>();

        Prova  prova1 =  new Prova("Matemática", "26/07/2017");
                        //cria uma lista
        prova1.setTopicos(Arrays.asList("Geometria","Báskara"));

        provas.add(prova1);

        //o tipo do Array Adapter sempre será do mesmo tipo que será adicionado à ele
        //getActivity() - recupera o contexto
        ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(getActivity(),
                android.R.layout.simple_list_item_1,provas);

        //
        listViewProvas.setAdapter(adapter);

         return view;

    }
}
