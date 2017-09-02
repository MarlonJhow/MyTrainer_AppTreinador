package tcc.mytrainer.facade;

import tcc.mytrainer.database.Session;
import tcc.mytrainer.model.Treino;

/**
 * Created by Marlon on 27/08/2017.
 */

public class TreinoFacade {

    public static void salvarTreino(Treino treino) {
        treino.setId(Session.getId());
        Session.treinador.getTreinos().put(Session.getId(), treino);
        Session.mDatabase.child("Treinador").child(Session.treinador.getId()).child("treinos").setValue(Session.treinador.getTreinos());
    }

}
