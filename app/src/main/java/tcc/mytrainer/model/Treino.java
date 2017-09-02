package tcc.mytrainer.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Marlon on 03/08/2017.
 */

public class Treino {
    private String id;
    private String nome;
    private String descricao;
    private HashMap<String, Atividade> atividades = new HashMap<>();
    private HashMap<String, String> alunos= new HashMap<>();

    public Treino() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public HashMap<String, String> getAlunos() {
        return alunos;
    }

    public void setAlunos(HashMap<String, String> alunos) {
        this.alunos = alunos;
    }

    public HashMap<String, Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(HashMap<String, Atividade> atividades) {
        this.atividades = atividades;
    }
}
