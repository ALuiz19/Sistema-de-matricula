package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Curriculo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String semestre;
    private ArrayList<Disciplina> disciplinas;

    public Curriculo(String semestre) {
        this.semestre = semestre;
        this.disciplinas = new ArrayList<>();
    }
    
    public void addDisciplina(Disciplina disciplina) {
        if (disciplina != null && !disciplinas.contains(disciplina)) {
            disciplinas.add(disciplina);
        }
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }
    
    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }
}
