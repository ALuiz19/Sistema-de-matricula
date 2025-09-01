package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
// import java.io.Serializable;
// import java.util.ArrayList;
import java.util.List;
import models.Curso;
import models.Usuario;

public class SistemaDAO {
    private static final String FILENAME = "database.dat";

    public static void salvarDados(List<Usuario> usuarios, List<Curso> cursos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(usuarios);
            oos.writeObject(cursos);
        } catch (Exception e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public static Object[] carregarDados() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            List<Usuario> usuarios = (List<Usuario>) ois.readObject();
            List<Curso> cursos = (List<Curso>) ois.readObject();
            return new Object[]{usuarios, cursos};
        } catch (Exception e) {
            // System.out.println("Arquivo de dados não encontrado. Um novo será criado.");
            return null;
        }
    }
}
