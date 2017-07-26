package br.com.caelum.cadastro;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.List;

/**
 * Created by android6920 on 25/07/17.
 */
// Ex 11.2 Pg 148 Tarefa assincrona
public class EnviaAlunosTask extends AsyncTask <Object, Object, String >{


    private Context contx;
    private ProgressDialog pd;

    public EnviaAlunosTask(Context context){
        this.contx = context;
    }

    //executado na Thread principal
    @Override
    public void onPreExecute(){
        //Contexto, o título, o texto do progress bar, load definido ou não e se é cancelável
        pd = ProgressDialog.show(contx, "Aguarde...", "Realizando Serviço", true, false);

    }

    // executa algo em um thread auxiliare somente na thread auxiliar
    // aceita alteração de retorno e parametros, mas deve ser de acorodo com o AsyncTask ^
    // ... define a inexistencia de limite de parâmetros
    protected String doInBackground(Object... params) {
        AlunoDAO dao = new AlunoDAO(contx);
        List<Aluno> alunos = dao.getLista();
        String json = new AlunoConverter().toJson(alunos);
        return new WebClient().post(json);
    }


    // é acionado após o onPreExecute
    // executado na Thread principal

    public void onPostExecute(String result){
        //remove o progressdialog da tela
        pd.dismiss();
        Toast.makeText(contx,result,Toast.LENGTH_LONG).show();

    }

}
