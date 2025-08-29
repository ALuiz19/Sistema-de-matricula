package models;

import java.util.ArrayList;
import java.util.Objects;

import models.enums.StatusDisciplina;

public class Disciplina {

    private static final int MAX_ALUNOS = 60;
    private static final int MIN_ALUNOS = 3;

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
        if (alunos.size() >= MIN_ALUNOS && alunos.size() <= MAX_ALUNOS) {
            status = StatusDisciplina.ATIVA;
        } else {
            status = StatusDisciplina.INATIVA;
        }
    }

    public void addAluno(Aluno aluno) {
        Objects.requireNonNull(aluno, "Aluno não pode ser nulo");

        if (alunos.size() >= 60) {
            throw new IllegalStateException("Quantidade máxima de alunos atingida.");
        }

        if (alunos.contains(aluno)) {
            throw new IllegalStateException("Aluno já matriculado nessa disciplina.");
        }

        alunos.add(aluno);
    }

    public void removeAluno(Aluno aluno) {
        Objects.requireNonNull(aluno, "Aluno não pode ser nulo");

        if (!alunos.contains(aluno)) {
            throw new IllegalStateException("Aluno não matriculado nessa disciplina.");
        }

        alunos.remove(aluno);
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
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

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

}
