package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Professor implements Usuario, Serializable {

    private static final long serialVersionUID = 1L;
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
        this.disciplinas = new ArrayList<>(); // Inicializa a lista
    }

    public ArrayList<Aluno> vizualizaAlunos(Disciplina disciplina) {
        return disciplina.getAlunos();
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

    public void addDisciplina(Disciplina disciplina) {
        if (disciplinas == null) {
            disciplinas = new ArrayList<>();
        }
        if (!disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
        }
    }

    public void removeDisciplina(Disciplina disciplina) {
        if (disciplinas != null && disciplinas.contains(disciplina)) {
            disciplinas.remove(disciplina);
        }
    }

    @Override
    public String toString() {
        return "Professor: " + nome + " (Cadastro: " + numCadastro + ")";
    }
}
