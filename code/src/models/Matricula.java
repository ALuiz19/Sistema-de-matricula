package models;

import java.util.ArrayList;
import java.util.List;

import models.enums.StatusMatricula;

public class Matricula {

    private Aluno aluno;
    private Curso curso;
    private List<Disciplina> disciplinas = new ArrayList<>();
    private StatusMatricula status = StatusMatricula.EFETIVADA;
    private boolean periodoAtivo = false;

    public Matricula(Aluno aluno, Curso curso) {
        this.aluno = aluno;
        this.curso = curso;
    }

    public void addDisciplina(Disciplina d) {
        if (periodoAtivo && status == StatusMatricula.EFETIVADA) {
            disciplinas.add(d);
            d.addAluno(aluno);
        } else {
            System.out.println("Não é possível adicionar disciplina (período inativo ou matrícula inválida).");
        }
    }

    public void cancelarMatricula() {
        this.status = StatusMatricula.CANCELADA;
        this.periodoAtivo = false;
    }

    public void ativarPeriodo() {
        if (status == StatusMatricula.EFETIVADA) {
            periodoAtivo = true;
        }
    }

    public void encerrarPeriodo() {
        periodoAtivo = false;
    }

    public void notificarCobranca() {
        // TODO: implementar
    }

    public StatusMatricula getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Matrícula do aluno " + aluno + " no curso " + curso + " - Status: " + status;
    }
}
