package models;

import java.util.ArrayList;
import models.enums.StatusDisciplina;

public class Disciplina {

    private static int numMaxAlunos = 60;
    private static int numMinAlunos = 3;

    private String nome;
    private boolean obrigatorio;
    private StatusDisciplina status;
    private ArrayList<Aluno> alunos;
    private int creditos;

    public Disciplina(String nome, boolean obrigatorio, StatusDisciplina status, ArrayList<Aluno> alunos) {
        this.nome = nome;
        this.obrigatorio = obrigatorio;
        this.status = StatusDisciplina.AGUARDANDO;
        this.alunos = alunos;
    }

    public void encerraPeriodoMatricula() {
        if (alunos.size() >= 3 && alunos.size() <= 60) {
            status = StatusDisciplina.ATIVA;
        } else {
            status = StatusDisciplina.INATIVA;
        }
    }

    public void addAluno(Aluno aluno) {
        if (alunos.size() < 60) {
            alunos.add(aluno);
        } else {
            throw new ArrayIndexOutOfBoundsException("Quantidade de alunos completa.");
        }
    }

    public void removeAluno(Aluno aluno) {
        if (alunos.contains(aluno)) {
            alunos.remove(aluno);
        } else {
            throw new ArrayIndexOutOfBoundsException("Este aluno não está matriculado nessa disciplina.");
        }
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

    // public void setAlunos(ArrayList<Aluno> alunos) {
    // this.alunos = alunos;
    // }

    public int getNumMaxAlunos() {
        return numMaxAlunos;
    }

    public int getNumMinAlunos() {
        return numMinAlunos;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

}
