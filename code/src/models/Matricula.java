package models;

import java.util.ArrayList;
import models.enums.StatusMatricula;

public class Matricula {

    private ArrayList<Disciplina> disciplinas;
    private Aluno aluno;
    private StatusMatricula status;

    public Matricula(Aluno aluno, ArrayList<Disciplina> disciplinas) {
        this.aluno = aluno;
        this.disciplinas = disciplinas;
        this.status = StatusMatricula.EFETIVADA;
    }

    
    public Matricula criarMatricula(Aluno aluno, ArrayList<Disciplina> disciplinas) {
        // TODO: implementar
        return null;
    }

    public void cancelarMatricula(Matricula matricula) {
        // TODO: implementar
    }

    public boolean periodoMatriculaAtivo() {
        // TODO: implementar
        return false;
    }

    public void notificarCobranca() {
        // TODO: implementar
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public StatusMatricula getStatus() {
        return status;
    }

    public void setStatus(StatusMatricula status) {
        this.status = status;
    }
}
