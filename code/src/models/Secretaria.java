package models;

import java.io.Serializable;
import java.util.Date;

public class Secretaria implements Usuario, Serializable {

    private String nome;
    private String senha;
    private String login;

    public Secretaria(String nome, String senha, String login) {
        this.nome = nome;
        this.senha = senha;
        this.login = login;
    }

    public Curriculo geraCurriculo() {
        return new Curriculo(new Date(), new Date());
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }
}
