package models;

import java.io.Serializable;

public interface Usuario extends Serializable {
    
    String getNome();
    void setNome(String nome);

    String getSenha();
    void setSenha(String senha);

    String getLogin();
    void setLogin(String login);
}
