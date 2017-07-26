package br.com.caelum.cadastro;

import java.util.List;

/**
 * Created by android6920 on 26/07/17.
 */

public class Prova {
    String materia;
    String data;
    List<String> topicos;

    public Prova(String materia, String data) {
        this.materia = materia;
        this.data = data;
    }

    public List<String> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<String> topicos) {
        this.topicos = topicos;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


}
