package models;

import java.util.ArrayList;

public class Professor implements Usuario {

    private String nome;
    private String login;
    private String senha;
    private long numCadastro;
    private ArrayList<Disciplina> disciplinas;

    public Professor(String nome, String login, String senha, long numCadastro, ArrayList<Disciplina> disciplinas) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.numCadastro = numCadastro;
        this.disciplinas = disciplinas;
    }

    
    public ArrayList<Aluno> vizualizaAlunos(Disciplina disciplina) {
        // TODO: implementar
        return null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public long getNumCadastro() {
        return numCadastro;
    }

    public void setNumCadastro(long numCadastro) {
        this.numCadastro = numCadastro;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

}
