package br.com.caelum.cadastro;

import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by android6920 on 25/07/17.
 */

public class WebClient {
    public String post (String json){
        try {
            URL url = new URL("https://www.caelum.com.br/mobile");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type","application/json");
            con.setRequestProperty("Accept","application/json");
            con.setDoInput(true);
            con.setDoOutput(true);

            //manipulação de escrita de dados  //passando a referencia da conexão para poder ser impressa
            PrintStream saida = null;
            saida = new PrintStream(con.getOutputStream());
            saida.println(json);

            //realizar a conexão e executar tudo
            con.connect();

            //por onde os dados da conexão viram , pega como paremetro o stream de entrada;
            Scanner entrada = new Scanner(con.getInputStream());
            //verificar se tem conteudo
            if (entrada.hasNext()){
                return entrada.next();
            }else{
                return "Sem Reposta";
            }

        } catch (IOException e) {
            return "Sem Reposta";
        }

    }

}
