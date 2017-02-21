package com.rafaelamarraschi.agendaalunos.agendaalunos.modelo;

import java.io.Serializable;

/**
 * Created by Rafaela on 07/02/2017.
 */

public class Aluno implements Serializable{

    private long id;
    private String nome;
    private String endereco;
    private String telefone;
    private String site;
    private double nota;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return getId() + '-' + getNome();
    }

}
