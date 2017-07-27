package br.com.caelum.cadastro;

import android.widget.Button;

import java.io.Serializable;

/**
 * Created by android6920 on 19/07/17.
 */

public class Aluno implements Serializable{

    private String nome;
    private String telefone;
    private String endereco;
    private String site;
    private Double nota;
    private Long id;

    //Ativ 7.4 pag 118 foto do aluno
    private String caminhoFoto;



    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getSite() {
        return site;
    }

    public Double getNota() {
        return nota;
    }

    public Long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    @Override
    public String toString() {
        return this.getId() + "-" + this.getNome();
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }
}
