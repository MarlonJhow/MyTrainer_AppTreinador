package tcc.mytrainer.facade;

import tcc.mytrainer.database.Session;
import tcc.mytrainer.model.ContaBancaria;

/**
 * Created by Marlon on 07/09/2017.
 */

public class ContaFacade {
    public static void salvarConta(ContaBancaria contaBancaria) {
        if (contaBancaria.getId() == null) {
            contaBancaria.setId(Session.getId());
        }

        Session.treinador.setContaBancaria(contaBancaria);
        Session.mDatabase.child("Treinador").child(Session.treinador.getId()).child("contaBancaria").setValue(contaBancaria);
    }
}
