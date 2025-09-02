import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import models.Aluno;
import models.Curso;
import models.Disciplina;
import models.Professor;
import models.Secretaria;
import models.Usuario;
import models.Matricula;
import dao.SistemaDAO;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Curso> cursos = new ArrayList<>();
    private static Matricula matriculaAtiva = null;

    public static void main(String[] args) {
        carregarDados();

        // Adiciona um usuário da secretaria como padrão se não houver nenhum
        if (usuarios.stream().noneMatch(u -> u instanceof Secretaria)) {
            usuarios.add(new Secretaria("admin", "admin", "admin"));
        }

        while (true) {
            System.out.println("\n==== BEM-VINDO AO SISTEMA DE MATRÍCULAS ====");
            System.out.print("Login: ");
            String login = sc.nextLine();
            System.out.print("Senha: ");
            String senha = sc.nextLine();

            Usuario usuarioLogado = autenticarUsuario(login, senha);

            if (usuarioLogado != null) {
                System.out.println("Login bem-sucedido! Bem-vindo(a), " + usuarioLogado.getNome());
                if (usuarioLogado instanceof Secretaria) {
                    menuSecretaria((Secretaria) usuarioLogado);
                } else if (usuarioLogado instanceof Aluno) {
                    menuAluno((Aluno) usuarioLogado);
                } else if (usuarioLogado instanceof Professor) {
                    menuProfessor((Professor) usuarioLogado);
                }
            } else {
                System.out.println("Login ou senha inválidos.");
            }
        }
    }

    // Métodos de Menu
    private static void menuSecretaria(Secretaria secretaria) {
        while (true) {
            System.out.println("\n==== MENU SECRETARIA ====");
            System.out.println("1 - Gerenciar Alunos");
            System.out.println("2 - Gerenciar Professores");
            System.out.println("3 - Gerenciar Cursos e Disciplinas");
            System.out.println("4 - Gerenciamento Administrativo");
            System.out.println("5 - Encerrar Período de Matrículas");
            System.out.println("0 - Logout (Salvar e Sair)");

            int opc = lerOpcao();

            switch (opc) {
                case 1:
                    gerenciarAlunos();
                    break;
                case 2:
                    gerenciarProfessores();
                    break;
                case 3:
                    gerenciarCursosDisciplinas();
                    break;
                case 4:
                    gerenciamentoAdministrativo();
                    break;
                case 5:
                    encerrarPeriodoMatriculas();
                    break;
                case 0:
                    salvarDados();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuAluno(Aluno aluno) {
        if (aluno.getMatriculas() == null || aluno.getMatriculas().isEmpty()) {
             System.out.println("Você ainda não está matriculado em um curso. A secretaria precisa fazer isso.");
             return;
        }
        matriculaAtiva = aluno.getMatriculas().get(0); // Simplificado para a primeira matrícula
        matriculaAtiva.ativarPeriodo();

        while (true) {
            System.out.println("\n==== MENU ALUNO ====");
            System.out.println("1 - Matricular-se em Disciplinas");
            System.out.println("2 - Cancelar Matrícula em Disciplina");
            System.out.println("3 - Ver Disciplinas Matriculadas");
            System.out.println("0 - Logout (Salvar e Sair)");

            int opc = lerOpcao();

            switch (opc) {
                case 1:
                    matricularEmDisciplina(aluno);
                    break;
                case 2:
                    cancelarMatriculaDisciplina(aluno);
                    break;
                case 3:
                    verDisciplinasMatriculadas();
                    break;
                case 0:
                    matriculaAtiva.encerrarPeriodo();
                    salvarDados();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void menuProfessor(Professor professor) {
         while (true) {
            System.out.println("\n==== MENU PROFESSOR ====");
            System.out.println("1 - Ver Alunos por Disciplina");
            System.out.println("0 - Logout");

            int opc = lerOpcao();
            switch (opc) {
                case 1:
                    visualizarAlunosPorDisciplina(professor);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    // Funcionalidades da Secretaria
    private static void gerenciamentoAdministrativo() {
        while (true) {
            System.out.println("\n-- Gerenciamento Administrativo --");
            System.out.println("1 - Cadastrar Novo Usuário da Secretaria");
            System.out.println("2 - Listar Usuários da Secretaria");
            System.out.println("0 - Voltar ao Menu Principal");
    
            int opc = lerOpcao();
    
            switch (opc) {
                case 1:
                    cadastrarNovaSecretaria();
                    break;
                case 2:
                    listarSecretarias();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void cadastrarNovaSecretaria() {
        System.out.print("Nome do novo usuário: ");
        String nome = sc.nextLine();
        System.out.print("Login: ");
        String login = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
    
        if (usuarios.stream().anyMatch(u -> u.getLogin().equals(login))) {
            System.out.println("Erro: Já existe um usuário com este login.");
            return;
        }
    
        usuarios.add(new Secretaria(nome, senha, login));
        System.out.println("Usuário da Secretaria cadastrado com sucesso!");
    }
    
    private static void listarSecretarias() {
        System.out.println("\n-- Lista de Usuários da Secretaria --");
        usuarios.stream()
                .filter(u -> u instanceof Secretaria)
                .forEach(u -> System.out.println("- " + u.getNome() + " (Login: " + u.getLogin() + ")"));
    }
    
    private static void gerenciarAlunos() {
        while (true) {
            System.out.println("\n-- Gerenciar Alunos --");
            System.out.println("1 - Cadastrar Novo Aluno (e matricular em curso)");
            System.out.println("2 - Matricular Aluno Existente em Curso");
            System.out.println("3 - Listar Alunos");
            System.out.println("0 - Voltar ao Menu Principal");
    
            int opc = lerOpcao();
    
            switch (opc) {
                case 1:
                    cadastrarNovoAluno();
                    break;
                case 2:
                    matricularAlunoExistente();
                    break;
                case 3:
                    listarAlunos();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private static void cadastrarNovoAluno() {
        System.out.print("Nome do Aluno: ");
        String nome = sc.nextLine();
        System.out.print("Login: ");
        String login = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        System.out.print("Número de Matrícula: ");
        long numMatricula = Long.parseLong(sc.nextLine());
    
        Aluno novoAluno = new Aluno(nome, login, senha, numMatricula);
        usuarios.add(novoAluno);
        System.out.println("Aluno cadastrado com sucesso!");
    
        System.out.println("\nAgora, matricule o novo aluno em um curso.");
        matricularAlunoEmCurso(novoAluno);
    }
    
    private static void matricularAlunoExistente() {
        System.out.println("\n-- Matricular Aluno Existente --");
        List<Aluno> todosAlunos = usuarios.stream()
                                        .filter(u -> u instanceof Aluno)
                                        .map(u -> (Aluno) u)
                                        .collect(Collectors.toList());
    
        if (todosAlunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado para matricular.");
            return;
        }
    
        System.out.println("Selecione o aluno:");
        for (int i = 0; i < todosAlunos.size(); i++) {
            Aluno a = todosAlunos.get(i);
            String statusCurso = (a.getMatriculas() == null || a.getMatriculas().isEmpty()) ?
                                 " (Sem curso)" :
                                 " (Matriculado em " + a.getMatriculas().get(0).getCurso().getNome() + ")";
            System.out.println(i + " - " + a.getNome() + statusCurso);
        }
        System.out.print("Escolha o número do aluno: ");
        int idxAluno = lerOpcao();
    
        if (idxAluno >= 0 && idxAluno < todosAlunos.size()) {
            Aluno alunoSelecionado = todosAlunos.get(idxAluno);
            if (alunoSelecionado.getMatriculas() != null && !alunoSelecionado.getMatriculas().isEmpty()) {
                System.out.println("Atenção: Este aluno já está matriculado. Uma nova matrícula substituirá a antiga.");
            }
            matricularAlunoEmCurso(alunoSelecionado);
        } else {
            System.out.println("Seleção de aluno inválida.");
        }
    }
    
    private static void matricularAlunoEmCurso(Aluno aluno) {
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado. Crie um curso primeiro.");
            return;
        }
    
        System.out.println("\nSelecione o curso para matricular " + aluno.getNome() + ":");
        listarCursos();
        System.out.print("Escolha o número do curso: ");
        int idxCurso = lerOpcao();
    
        if (idxCurso >= 0 && idxCurso < cursos.size()) {
            Curso cursoEscolhido = cursos.get(idxCurso);
            
            if (aluno.getMatriculas() != null) {
                aluno.getMatriculas().clear();
            }
            
            Matricula m = new Matricula(aluno, cursoEscolhido);
            aluno.addMatricula(m);
            System.out.println("Aluno(a) " + aluno.getNome() + " matriculado(a) com sucesso no curso " + cursoEscolhido.getNome() + "!");
        } else {
            System.out.println("Seleção de curso inválida.");
        }
    }
    
    private static void listarAlunos() {
        System.out.println("\n-- Lista de Alunos --");
        usuarios.stream()
                .filter(u -> u instanceof Aluno)
                .forEach(u -> System.out.println(((Aluno) u).toString()));
    }

    private static void gerenciarProfessores() {
        System.out.println("1 - Cadastrar Professor\n2 - Listar Professores");
        int opc = lerOpcao();
        if (opc == 1) {
            System.out.print("Nome do Professor: ");
            String nome = sc.nextLine();
            System.out.print("Login: ");
            String login = sc.nextLine();
            System.out.print("Senha: ");
            String senha = sc.nextLine();
            System.out.print("Número de Cadastro: ");
            long numCadastro = Long.parseLong(sc.nextLine());

            usuarios.add(new Professor(nome, login, senha, numCadastro));
            System.out.println("Professor cadastrado com sucesso!");
        } else if (opc == 2) {
            System.out.println("\n-- Lista de Professores --");
            usuarios.stream()
                    .filter(u -> u instanceof Professor)
                    .forEach(u -> System.out.println(((Professor) u).toString()));
        }
    }

    private static void gerenciarCursosDisciplinas() {
        while (true) {
            System.out.println("\n-- Gerenciar Cursos e Disciplinas --");
            System.out.println("1 - Criar Curso");
            System.out.println("2 - Adicionar Disciplina a Curso");
            System.out.println("3 - Atribuir/Alterar Professor de Disciplina");
            System.out.println("4 - Listar Cursos e Disciplinas");
            System.out.println("0 - Voltar ao Menu Principal");

            int opc = lerOpcao();
            switch (opc) {
                case 1:
                    criarCurso();
                    break;
                case 2:
                    adicionarDisciplinaACurso();
                    break;
                case 3:
                    atribuirProfessorADisciplina();
                    break;
                case 4:
                    listarCursosComDisciplinas();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void criarCurso() {
        System.out.print("Nome do curso: ");
        String nome = sc.nextLine();
        System.out.print("Créditos: ");
        int creditos = Integer.parseInt(sc.nextLine());
        cursos.add(new Curso(nome, creditos));
        System.out.println("Curso criado com sucesso!");
    }

    private static void adicionarDisciplinaACurso() {
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado. Crie um curso primeiro.");
            return;
        }
        listarCursos();
        System.out.print("Escolha o número do curso: ");
        int idxCurso = lerOpcao();
        
        if (idxCurso < 0 || idxCurso >= cursos.size()) {
            System.out.println("Seleção de curso inválida.");
            return;
        }
        Curso cursoSelecionado = cursos.get(idxCurso);

        System.out.print("Nome da disciplina: ");
        String nomeDisciplina = sc.nextLine();
        System.out.print("A disciplina é obrigatória? (s/n): ");
        boolean ehObrigatorio = sc.nextLine().equalsIgnoreCase("s");

        Professor professorSelecionado = selecionarProfessor();
        
        Disciplina novaDisciplina = new Disciplina(nomeDisciplina, ehObrigatorio);
        if (professorSelecionado != null) {
            novaDisciplina.setProfessor(professorSelecionado);
            professorSelecionado.addDisciplina(novaDisciplina);
        }

        cursoSelecionado.addDisciplina(novaDisciplina);
        System.out.println("Disciplina '" + nomeDisciplina + "' adicionada ao curso '" + cursoSelecionado.getNome() + "'!");
    }

    private static void atribuirProfessorADisciplina() {
        Disciplina disciplinaSelecionada = selecionarDisciplina();
        if (disciplinaSelecionada == null) {
            return;
        }
        
        System.out.println("Professor atual: " + (disciplinaSelecionada.getProfessor() != null ? disciplinaSelecionada.getProfessor().getNome() : "Nenhum"));

        Professor novoProfessor = selecionarProfessor();
        
        Professor professorAntigo = disciplinaSelecionada.getProfessor();
        if (professorAntigo != null) {
            professorAntigo.removeDisciplina(disciplinaSelecionada);
        }

        disciplinaSelecionada.setProfessor(novoProfessor);

        if (novoProfessor != null) {
            novoProfessor.addDisciplina(disciplinaSelecionada);
            System.out.println("Professor " + novoProfessor.getNome() + " atribuído à disciplina " + disciplinaSelecionada.getNome() + " com sucesso!");
        } else {
            System.out.println("Disciplina '" + disciplinaSelecionada.getNome() + "' agora está sem professor.");
        }
    }


     private static void encerrarPeriodoMatriculas() {
        System.out.println("Encerrando período de matrículas e validando disciplinas...");
        for (Curso curso : cursos) {
            for (Disciplina disciplina : curso.getDisciplinas()) {
                String status = disciplina.encerraPeriodoMatricula();
                System.out.println("Disciplina '" + disciplina.getNome() + "': " + status);
            }
        }
        System.out.println("Período encerrado.");
    }

    // Funcionalidades do Aluno
    private static void matricularEmDisciplina(Aluno aluno) {
        System.out.println("\n-- Disciplinas Disponíveis --");
        Curso cursoDoAluno = matriculaAtiva.getCurso();
        ArrayList<Disciplina> disciplinasDisponiveis = cursoDoAluno.getDisciplinas();

        for (int i = 0; i < disciplinasDisponiveis.size(); i++) {
            Disciplina d = disciplinasDisponiveis.get(i);
            System.out.println(i + " - " + d.getNome() + (d.isObrigatorio() ? " (Obrigatória)" : " (Optativa)") + " - Vagas: " + (60 - d.getAlunos().size()));
        }

        System.out.print("Escolha o número da disciplina para se matricular: ");
        int idxDisciplina = lerOpcao();

        if (idxDisciplina >= 0 && idxDisciplina < disciplinasDisponiveis.size()) {
            Disciplina disciplinaEscolhida = disciplinasDisponiveis.get(idxDisciplina);
            try {
                matriculaAtiva.addDisciplina(disciplinaEscolhida);
                System.out.println("Matrícula na disciplina '" + disciplinaEscolhida.getNome() + "' realizada com sucesso!");
                 matriculaAtiva.notificarCobranca();
            } catch (IllegalStateException e) {
                System.out.println("Erro ao matricular: " + e.getMessage());
            }
        } else {
            System.out.println("Disciplina inválida.");
        }
    }

     private static void cancelarMatriculaDisciplina(Aluno aluno) {
        System.out.println("\n-- Suas Disciplinas --");
        List<Disciplina> disciplinasMatriculadas = matriculaAtiva.getDisciplinas();

        if (disciplinasMatriculadas.isEmpty()) {
            System.out.println("Você não está matriculado em nenhuma disciplina.");
            return;
        }

        for (int i = 0; i < disciplinasMatriculadas.size(); i++) {
            System.out.println(i + " - " + disciplinasMatriculadas.get(i).getNome());
        }

        System.out.print("Escolha o número da disciplina para cancelar a matrícula: ");
        int idxDisciplina = lerOpcao();

        if (idxDisciplina >= 0 && idxDisciplina < disciplinasMatriculadas.size()) {
            Disciplina disciplinaEscolhida = disciplinasMatriculadas.get(idxDisciplina);
            try {
                matriculaAtiva.removeDisciplina(disciplinaEscolhida);
                System.out.println("Matrícula na disciplina '" + disciplinaEscolhida.getNome() + "' cancelada.");
            } catch (IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } else {
            System.out.println("Disciplina inválida.");
        }
    }

     private static void verDisciplinasMatriculadas() {
        System.out.println("\n-- Disciplinas Matriculadas --");
        List<Disciplina> disciplinasMatriculadas = matriculaAtiva.getDisciplinas();
        if (disciplinasMatriculadas.isEmpty()) {
            System.out.println("Nenhuma disciplina matriculada.");
        } else {
            disciplinasMatriculadas.forEach(d -> System.out.println("- " + d.getNome()));
        }
    }


    // Funcionalidades do Professor
    private static void visualizarAlunosPorDisciplina(Professor professor) {
        System.out.println("\n-- Suas Disciplinas --");
        List<Disciplina> disciplinasProfessor = professor.getDisciplinas();
        
        if(disciplinasProfessor == null || disciplinasProfessor.isEmpty()){
             System.out.println("Você não está lecionando em nenhuma disciplina.");
             return;
        }

        for (int i = 0; i < disciplinasProfessor.size(); i++) {
            System.out.println(i + " - " + disciplinasProfessor.get(i).getNome());
        }

        System.out.print("Escolha uma disciplina para ver os alunos: ");
        int idx = lerOpcao();

        if (idx >= 0 && idx < disciplinasProfessor.size()) {
            Disciplina d = disciplinasProfessor.get(idx);
            System.out.println("\n-- Alunos em " + d.getNome() + " --");
            ArrayList<Aluno> alunos = professor.vizualizaAlunos(d);
            if (alunos.isEmpty()) {
                System.out.println("Nenhum aluno matriculado.");
            } else {
                alunos.forEach(aluno -> System.out.println("- " + aluno.getNome()));
            }
        } else {
            System.out.println("Opção inválida.");
        }
    }


    // Métodos Auxiliares
    private static Usuario autenticarUsuario(String login, String senha) {
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                return u;
            }
        }
        return null;
    }
    
    private static int lerOpcao() {
        int opc = -1;
        try {
            opc = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Por favor, insira um número válido.");
            opc = -1;
        }
        return opc;
    }

    private static Professor selecionarProfessor() {
        List<Professor> professores = usuarios.stream()
                .filter(u -> u instanceof Professor)
                .map(u -> (Professor) u)
                .collect(Collectors.toList());

        if (professores.isEmpty()) {
            System.out.println("Aviso: Nenhum professor cadastrado.");
            return null;
        }

        System.out.println("\nSelecione um professor:");
        for (int i = 0; i < professores.size(); i++) {
            System.out.println(i + " - " + professores.get(i).getNome());
        }
        System.out.println(professores.size() + " - Nenhum (deixar sem professor)");
        System.out.print("Escolha uma opção: ");
        int idxProf = lerOpcao();

        if (idxProf >= 0 && idxProf < professores.size()) {
            return professores.get(idxProf);
        }
        return null;
    }

    private static Disciplina selecionarDisciplina() {
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso (e, portanto, nenhuma disciplina) cadastrado.");
            return null;
        }

        List<Disciplina> todasDisciplinas = new ArrayList<>();
        System.out.println("\nSelecione uma disciplina:");
        int count = 0;
        for (Curso curso : cursos) {
            for (Disciplina disciplina : curso.getDisciplinas()) {
                System.out.println(count + " - " + disciplina.getNome() + " (Curso: " + curso.getNome() + ")");
                todasDisciplinas.add(disciplina);
                count++;
            }
        }
        
        if (todasDisciplinas.isEmpty()) {
            System.out.println("Nenhuma disciplina cadastrada em nenhum curso.");
            return null;
        }

        System.out.print("Escolha o número da disciplina: ");
        int idxDisc = lerOpcao();
        if (idxDisc >= 0 && idxDisc < todasDisciplinas.size()) {
            return todasDisciplinas.get(idxDisc);
        }
        System.out.println("Seleção inválida.");
        return null;
    }

    private static void listarCursos() {
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");
            return;
        }
        System.out.println("\n-- Lista de Cursos --");
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println(i + " - " + cursos.get(i).getNome());
        }
    }

    private static void listarCursosComDisciplinas() {
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso cadastrado.");
            return;
        }
        System.out.println("\n-- Cursos e Disciplinas --");
        for (Curso curso : cursos) {
            System.out.println("Curso: " + curso.getNome());
            if (curso.getDisciplinas().isEmpty()) {
                System.out.println("  (Nenhuma disciplina cadastrada)");
            } else {
                for (Disciplina disciplina : curso.getDisciplinas()) {
                    String nomeProfessor = disciplina.getProfessor() != null ? disciplina.getProfessor().getNome() : "Não atribuído";
                    System.out.println("  - " + disciplina.getNome() + " (" + (disciplina.isObrigatorio() ? "Obrigatória" : "Optativa") + ") | Professor: " + nomeProfessor);
                }
            }
        }
    }

    // Persistência
    private static void carregarDados() {
        Object[] dados = SistemaDAO.carregarDados();
        if (dados != null) {
            usuarios = (List<Usuario>) dados[0];
            cursos = (List<Curso>) dados[1];
            System.out.println("Dados carregados com sucesso.");
        } else {
            System.out.println("Nenhum dado encontrado. Iniciando um novo sistema.");
        }
    }

    private static void salvarDados() {
        SistemaDAO.salvarDados(usuarios, cursos);
        System.out.println("Dados salvos com sucesso. Encerrando.");
    }
}
