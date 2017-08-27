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
}
