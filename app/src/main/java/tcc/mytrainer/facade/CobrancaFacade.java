package tcc.mytrainer.facade;

import tcc.mytrainer.database.Session;
import tcc.mytrainer.model.Cobranca;
import tcc.mytrainer.model.Treinador;

/**
 * Created by marlonjhow on 04/10/17.
 */

public class CobrancaFacade {


    public static void saveOrUpdate(Cobranca cobranca) {
        Treinador treinador = Session.treinador;
        cobranca.setIdTreinador(treinador.getId());

        if(cobranca.getId() == null){
            String id = Session.getId();
            cobranca.setId(id);
        }

        Session.mDatabase.child("Treinador").child(Session.treinador.getId()).child("cobrancas").child(cobranca.getId()).setValue(cobranca.getId());
        Session.mDatabase.child("Aluno").child(cobranca.getIdAluno()).child("cobrancas").child(cobranca.getId()).setValue(cobranca.getId());
        Session.mDatabase.child("Cobranca").child(cobranca.getId()).setValue(cobranca);
    }
}
