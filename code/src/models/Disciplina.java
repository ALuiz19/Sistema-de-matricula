package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import models.enums.StatusDisciplina;

public class Disciplina implements Serializable {

    private static final int MAX_ALUNOS = 60;
    private static final int MIN_ALUNOS = 3;

    private String nome;
    private boolean obrigatorio;
    private StatusDisciplina status = StatusDisciplina.AGUARDANDO;
    private ArrayList<Aluno> alunos = new ArrayList<>();

    public Disciplina(String nome, boolean ehObrigatorio) {
        this.nome = nome;
        this.obrigatorio = ehObrigatorio;
    }

    public String encerraPeriodoMatricula() {
        if (alunos.size() >= MIN_ALUNOS) {
            status = StatusDisciplina.ATIVA;
            return "Ativa com " + alunos.size() + " alunos.";
        } else {
            status = StatusDisciplina.INATIVA;
            return "Cancelada por falta de alunos (Inscritos: " + alunos.size() + ").";
        }
    }

    public void addAluno(Aluno aluno) {
        Objects.requireNonNull(aluno, "Aluno não pode ser nulo");

        if (alunos.size() >= MAX_ALUNOS) {
            throw new IllegalStateException("Quantidade máxima de alunos atingida ("+ MAX_ALUNOS +").");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
