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
        if (!periodoAtivo || status != StatusMatricula.EFETIVADA) {
             throw new IllegalStateException("Período de matrículas está fechado ou sua matrícula não está ativa.");
        }
        
        long obrigatorias = disciplinas.stream().filter(Disciplina::isObrigatorio).count();
        long optativas = disciplinas.size() - obrigatorias;

        if (d.isObrigatorio() && obrigatorias >= 4) {
            throw new IllegalStateException("Limite de 4 disciplinas obrigatórias atingido.");
        }
        if (!d.isObrigatorio() && optativas >= 2) {
             throw new IllegalStateException("Limite de 2 disciplinas optativas atingido.");
        }
        
        d.addAluno(aluno); // Lança exceção se a disciplina estiver cheia
        disciplinas.add(d);
    }
    
    public void removeDisciplina(Disciplina d) {
        if (!periodoAtivo) {
            throw new IllegalStateException("Não é possível remover disciplinas fora do período de matrículas.");
        }
        if(disciplinas.contains(d)){
            disciplinas.remove(d);
            d.removeAluno(aluno);
        } else {
             throw new IllegalStateException("Você não está matriculado nesta disciplina.");
        }
    }


    public void cancelarMatricula() {
        this.status = StatusMatricula.CANCELADA;
        this.periodoAtivo = false;
        // Remove o aluno de todas as disciplinas em que estava inscrito
        for (Disciplina d : disciplinas) {
            d.removeAluno(aluno);
        }
        disciplinas.clear();
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
        System.out.println("--> [SISTEMA DE COBRANÇA]: Notificado para cobrar o aluno " + aluno.getNome() + " pelas disciplinas matriculadas.");
    }

    public StatusMatricula getStatus() {
        return status;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Curso getCurso() {
        return curso;
    }
    
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    @Override
    public String toString() {
        return "Matrícula do aluno " + aluno.getNome() + " no curso " + curso.getNome() + " - Status: " + status;
    }
}
