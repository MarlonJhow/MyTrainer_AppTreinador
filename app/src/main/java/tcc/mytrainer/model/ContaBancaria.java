package tcc.mytrainer.model;

/**
 * Created by Marlon on 07/09/2017.
 */

public class ContaBancaria {

    private String id;
    private String nomeTitular;
    private String cpf;
    private String agencia;
    private String conta;
    private String contaDigito;

    public ContaBancaria() {
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getContaDigito() {
        return contaDigito;
    }

    public void setContaDigito(String contaDigito) {
        this.contaDigito = contaDigito;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
