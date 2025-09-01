import java.util.*;

import models.Aluno;
import models.Curso;
import models.Disciplina;
import models.Matricula;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Base de dados simples em memória
        List<Aluno> alunos = new ArrayList<>();
        List<Curso> cursos = new ArrayList<>();

        while (true) {
            System.out.println("\n==== MENU SISTEMA DE MATRÍCULAS ====");
            System.out.println("1 - Cadastrar aluno");
            System.out.println("2 - Criar curso");
            System.out.println("3 - Adicionar disciplina a curso");
            System.out.println("4 - Efetivar matrícula");
            System.out.println("5 - Cancelar matrícula");
            System.out.println("6 - Listar alunos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            int opc = sc.nextInt();
            sc.nextLine();

            switch (opc) {
                case 1 -> {
                    System.out.print("Login : ");
                    String login = sc.nextLine();
                    System.out.print("Senha: ");
                    String senha = sc.nextLine();
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("numMatricula: ");
                    Long numMatricula = sc.nextLong();
                    alunos.add(new Aluno(login,
                            senha, nome, numMatricula));
                }
                case 2 -> {
                    System.out.print("Nome do curso: ");
                    String nome = sc.nextLine();
                    System.out.print("Créditos: ");
                    int creditos = sc.nextInt();
                    sc.nextLine();
                    cursos.add(new Curso(nome, creditos));
                }
                case 3 -> {
                    if (cursos.isEmpty()) {
                        System.out.println("Nenhum curso cadastrado.");
                        break;
                    }
                    System.out.println("Selecione curso:");
                    for (int i = 0; i < cursos.size(); i++) {
                        System.out.println(i + " - " + cursos.get(i));
                    }
                    int idxCurso = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nome da disciplina: ");
                    String nomeDisciplina = sc.nextLine();
                    System.out.print("Disciplina obrigatória ? (true or false) ");
                    boolean ehObrigatorio = sc.nextBoolean();
                    cursos.get(idxCurso).addDisciplina(new Disciplina(nomeDisciplina, ehObrigatorio));
                }
                case 4 -> {
                    if (alunos.isEmpty() || cursos.isEmpty()) {
                        System.out.println("Cadastre alunos e cursos antes.");
                        break;
                    }
                    System.out.println("Selecione aluno:");
                    for (int i = 0; i < alunos.size(); i++) {
                        System.out.println(i + " - " + alunos.get(i));
                    }
                    int idxAluno = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Selecione curso:");
                    for (int i = 0; i < cursos.size(); i++) {
                        System.out.println(i + " - " + cursos.get(i));
                    }
                    int idxCurso = sc.nextInt();
                    sc.nextLine();

                    Matricula m = new Matricula(alunos.get(idxAluno), cursos.get(idxCurso));
                    alunos.get(idxAluno).addMatricula(m);
                    m.ativarPeriodo();
                    System.out.println("Matrícula efetuada: " + m);
                }
                case 5 -> {
                    System.out.println("Cancelamento de matrícula ainda em construção.");
                }
                case 6 -> {
                    for (Aluno a : alunos) {
                        System.out.println(a);
                    }
                }
                case 0 -> {
                    System.out.println("Encerrando...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
