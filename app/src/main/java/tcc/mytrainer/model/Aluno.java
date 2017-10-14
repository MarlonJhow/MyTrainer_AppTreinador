package tcc.mytrainer.model;

import android.widget.ImageView;

import java.util.HashMap;

/**
 * Created by Marlon on 15/09/2017.
 */

public class Aluno {
    private String id;
    private String nome;
    private String email;
    private String fotoUrl;
    private HashMap<String, String> idCobrancas;

    public Aluno() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public HashMap<String, String> getIdCobrancas() {
        return idCobrancas;
    }

    public void setIdCobrancas(HashMap<String, String> idCobrancas) {
        this.idCobrancas = idCobrancas;
    }
}
