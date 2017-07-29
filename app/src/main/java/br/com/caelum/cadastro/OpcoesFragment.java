package br.com.caelum.cadastro;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by android6920 on 28/07/17.
 */

public class OpcoesFragment extends DialogFragment {

    PreferenciasDAO prefs;
    int ordem;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.prefs = new PreferenciasDAO(getActivity());
        this.ordem = prefs.getOrdem();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Listar Contatos em ordem..");
        builder.setSingleChoiceItems(new String[]{"Alfabética", "Cronológica"},ordem,
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        prefs.salva(which);
                        ((ListaAlunosActivity) getActivity()).carregarLista();
                    }

        });






        return builder.create();





        //return super.onCreateDialog(savedInstanceState);
    }
}
