package models;

import java.util.ArrayList;

public class Aluno implements Usuario {

    private String login;
    private String senha;
    private String nome;
    private long numMatricula;
    private ArrayList<Matricula> matriculas;

    public Aluno(String nome, String login, String senha, long numMatricula) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.numMatricula = numMatricula;
        this.matriculas = new ArrayList<>(); // Inicializa a lista
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

    public long getNumMatricula() {
        return numMatricula;
    }

    public void setNumMatricula(long numMatricula) {
        this.numMatricula = numMatricula;
    }

    public ArrayList<Matricula> getMatriculas() {
        return matriculas;
    }

    public void addMatricula(Matricula m) {
        if (this.matriculas == null) {
            this.matriculas = new ArrayList<>();
        }
        matriculas.add(m);
    }

    public void cancelarMatricula(Matricula m) {
        if (matriculas.contains(m)) {
            m.cancelarMatricula();
        }
    }

    @Override
    public String toString() {
        return "Aluno: " + nome + " (Matrícula: " + numMatricula + ")";
    }
}
