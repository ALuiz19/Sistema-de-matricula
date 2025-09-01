package models;

import java.util.ArrayList;

public class Professor implements Usuario {

    private String nome;
    private String login;
    private String senha;
    private long numCadastro;
    private ArrayList<Disciplina> disciplinas;

    public Professor(String nome, String login, String senha, long numCadastro) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.numCadastro = numCadastro;
        this.disciplinas = new ArrayList<>();
    }

    public ArrayList<Aluno> vizualizaAlunos(Disciplina disciplina) {
        return disciplina.getAlunos();
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
    public String getLogin() {
        return login;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String getSenha() {
        return senha;
    }

    @Override
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

    public void addDisciplina(Disciplina disciplina) {
        if (!disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
        }
    }

    public void removeDisciplina(Disciplina disciplina) {
        if (disciplinas.contains(disciplina)) {
            disciplinas.remove(disciplina);
        }
    }

    @Override
    public String toString() {
        return "Professor: " + nome + " (Cadastro: " + numCadastro + ")";
    }
}
