package br.com.caelum.cadastro;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by android6920 on 25/07/17.
 */

public class AlunoConverter {

    public String toJson(List<Aluno> aluno){
        // [ ] - início e fim de uma lista no JSon
        // { } - início e fim de um item da lista

        //Biblioteca do google para converter em Json, chamada de Gson
        //importar a biblioteca via Gradle
        Gson gson = new Gson();

        //Existe metodo de um json para um obj e vice-versa

        String strJson = "{list: [{aluno:" + gson.toJson(aluno) + "}]}";

        return strJson;

    }

    /*public Object toObject (String str){

        Gson gson = new Gson();
        Object obj = gson.to
        return
    }*/
}
