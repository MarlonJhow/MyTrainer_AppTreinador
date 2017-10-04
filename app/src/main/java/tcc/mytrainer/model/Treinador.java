package tcc.mytrainer.model;

import java.util.HashMap;

/**
 * Created by Marlon on 26/08/2017.
 */

public class Treinador {

    private String id;
    private String email;
    private String password;
    private ContaPagSeguro contaPagSeguro;
    private HashMap<String, Treino> treinos = new HashMap<String, Treino>();
    private HashMap<String, Cobranca> cobrancas = new HashMap<String, Cobranca>();
    private HashMap<String, String> idAlunos = new HashMap<String, String>();

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ContaPagSeguro getContaPagSeguro() {
        return contaPagSeguro;
    }

    public void setContaPagSeguro(ContaPagSeguro contaPagSeguro) {
        this.contaPagSeguro = contaPagSeguro;
    }

    public HashMap<String, String> getIdAlunos() {
        return idAlunos;
    }

    public void setIdAlunos(HashMap<String, String> idAlunos) {
        this.idAlunos = idAlunos;
    }

    public HashMap<String, Cobranca> getCobrancas() {
        return cobrancas;
    }

    public void setCobrancas(HashMap<String, Cobranca> cobrancas) {
        this.cobrancas = cobrancas;
    }
}
