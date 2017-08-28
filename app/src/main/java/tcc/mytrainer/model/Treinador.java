package tcc.mytrainer.model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Marlon on 26/08/2017.
 */

public class Treinador {

    private String email;
    private String password;
    private HashMap<String, Treino> treinos;

    public Treinador() {
    }

    public Treinador(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void update(Treinador t) {
        this.email = t.getEmail();
        this.password = t.getPassword();
        this.treinos = t.getTreinos();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HashMap<String, Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(HashMap<String, Treino> treinos) {
        this.treinos = treinos;
    }
}
