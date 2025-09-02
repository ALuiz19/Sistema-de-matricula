package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import models.enums.StatusMatricula;

public class Matricula implements Serializable {

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
            d.addAluno(aluno);
            disciplinas.add(d);
        } else {
            throw new IllegalStateException("Não é possível adicionar disciplina (período inativo ou matrícula inválida).");
        }
    }
    
    public void removeDisciplina(Disciplina d) {
        if (periodoAtivo && status == StatusMatricula.EFETIVADA) {
            d.removeAluno(aluno);
            disciplinas.remove(d);
        } else {
            throw new IllegalStateException("Não é possível remover disciplina (período inativo ou matrícula inválida).");
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
        System.out.println("Sistema de cobranças notificado para o aluno: " + aluno.getNome());
    }

    public StatusMatricula getStatus() {
        return status;
    }
    
    public Curso getCurso(){
        return this.curso;
    }
    
    public List<Disciplina> getDisciplinas() {
        return this.disciplinas;
    }

    @Override
    public String toString() {
        return "Matrícula do aluno " + aluno.getNome() + " no curso " + curso.getNome() + " - Status: " + status;
    }
}
