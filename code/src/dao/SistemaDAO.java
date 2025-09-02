package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import models.Curso;
import models.Curriculo;
import models.Usuario;

public class SistemaDAO {

    private static final String NOME_ARQUIVO = "database.dat";

    public static void salvarDados(List<Usuario> usuarios, List<Curso> cursos, List<Curriculo> curriculos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO))) {
            oos.writeObject(usuarios);
            oos.writeObject(cursos);
            oos.writeObject(curriculos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public static Object[] carregarDados() {
        File arquivo = new File(NOME_ARQUIVO);
        if (!arquivo.exists()) {
            return null; 
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(NOME_ARQUIVO))) {
            List<Usuario> usuarios = (List<Usuario>) ois.readObject();
            List<Curso> cursos = (List<Curso>) ois.readObject();
            List<Curriculo> curriculos = (List<Curriculo>) ois.readObject();
            return new Object[]{usuarios, cursos, curriculos};
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar os dados: " + e.getMessage());
            return null;
        }
    }
}

