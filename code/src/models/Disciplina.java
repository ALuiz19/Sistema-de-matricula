package models;

import java.util.ArrayList;
import models.enums.StatusDisciplina;

public class Disciplina {
    
    private String nome;
    private boolean obrigatorio;
    private StatusDisciplina status;
    private ArrayList<Aluno> alunos;
    private static int numMaxAlunos = 60;
    private static int numMinAlunos = 3;

    public Disciplina(String nome, boolean obrigatorio, StatusDisciplina status, ArrayList<Aluno> alunos) {
        this.nome = nome;
        this.obrigatorio = obrigatorio;
        this.status = status;
        this.alunos = alunos;
    }


    public void encerraPeriodoMatricula() {
        // TODO: implementar
    }

    public void addAluno(Aluno aluno) {
        // TODO: implementar
    }

    public void removeAluno(Aluno aluno) {
        // TODO: implementar
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isObrigatorio() {
        return obrigatorio;
    }

    public void setObrigatorio(boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    public StatusDisciplina getStatus() {
        return status;
    }

    public void setStatus(StatusDisciplina status) {
        this.status = status;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }

    public int getNumMaxAlunos() {
        return numMaxAlunos;
    }

    public int getNumMinAlunos() {
        return numMinAlunos;
    }

}
