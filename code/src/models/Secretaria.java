package models;

import java.util.Date;

public class Secretaria implements Usuario {

    private String nome;
    private String senha;
    private String login;

    public Secretaria(String nome, String senha, String login) {
        this.nome = nome;
        this.senha = senha;
        this.login = login;
    }

    /**
     * Cria um novo currículo para um semestre.
     * @param dataInicio A data de início do semestre.
     * @param dataFim A data de fim do semestre.
     * @return um novo objeto Curriculo.
     */
    public Curriculo geraCurriculo(Date dataInicio, Date dataFim) {
        System.out.println("Gerando currículo para o novo semestre.");
        return new Curriculo(dataInicio, dataFim);
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

    @Override
    public String toString() {
        return "Secretaria: " + nome;
    }
}
