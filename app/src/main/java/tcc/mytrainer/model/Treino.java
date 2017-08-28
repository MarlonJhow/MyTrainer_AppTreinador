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
    private Long id;
    private String nome;
    private String descricao;
    private HashMap<String, Atividade> atividades = new HashMap<>();
    private HashMap<String, String> alunos= new HashMap<>();

    public Treino() {
    }

    public Treino(JSONObject jsonObject) throws JSONException {
        this.id = Long.parseLong(jsonObject.get("id").toString());
        this.nome = jsonObject.get("nome").toString();
        this.descricao = jsonObject.get("descricao").toString();
    }

    public static List<Treino> toList(JSONArray jsonArray) throws JSONException {
        List<Treino> treinos = new ArrayList<>();
        for (int index = 0; index < jsonArray.length(); index++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(index);
            treinos.add(new Treino(jsonObject));
        }
        return treinos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
