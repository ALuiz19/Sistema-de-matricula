package models;

import java.util.ArrayList;
import java.util.Objects;

public class Curso {

    private String nome;
    private int creditos;
    private ArrayList<Disciplina> disciplinas;

    public Curso(String nome, int creditos) {
        this.nome = nome;
        this.creditos = creditos;
        this.disciplinas = new ArrayList<>();
    }

    public void addDiscplina(Disciplina disciplina) {
        Objects.requireNonNull(disciplina, "Disciplina não pode ser nula");

        if (disciplinas.contains(disciplina)) {
            throw new IllegalStateException("Disciplina já existente neste curso.");
        }

        disciplinas.add(disciplina);
    }

    public void removeDisciplina(Disciplina disciplina) {
        Objects.requireNonNull(disciplina, "Disciplina não pode ser nula");

        if (!disciplinas.contains(disciplina)) {
            throw new IllegalStateException("Disciplina não existente neste curso.");
        }

        disciplinas.remove(disciplina);

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public ArrayList<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(ArrayList<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }
}
