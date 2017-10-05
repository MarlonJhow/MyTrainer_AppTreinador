package tcc.mytrainer.model;

import java.util.Date;

/**
 * Created by marlonjhow on 04/10/17.
 */

public class Cobranca {

    public enum Periodo {
        UNICO("Unico"), MENSAL("Mensal");

        String label;

        Periodo(String label){
            this.label = label;
        }

        @Override
        public String toString() {
            return this.label;
        }

        public static Periodo get(String entrada) {
            Periodo periodo;

            periodo = entrada.equals(UNICO) ? UNICO : MENSAL;

            return periodo;
        }
    }

    private String id;
    private String idAluno;
    private Periodo periodo;
    private Date vencimento;
    private Double valor;

    public Cobranca() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(String idAluno) {
        this.idAluno = idAluno;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
