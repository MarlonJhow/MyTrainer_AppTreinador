package tcc.mytrainer.model;

import java.util.List;

/**
 * Created by Marlon on 03/08/2017.
 */

public class Treino {
    private Long id;
    private String nome;
    private String descricao;

    public Treino() {
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

}