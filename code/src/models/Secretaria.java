package models;

public class Secretaria implements Usuario {
    private String nome;
    private String senha;
    private String login;

    public Secretaria(String nome, String senha, String login) {
        this.nome = nome;
        this.senha = senha;
        this.login = login;
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

    // Método específico do UML
    public Curriculo geraCurriculo() {
        // TODO: implementar regra para gerar Curriculo
        return null;
    }
}
