package tcc.mytrainer.database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import tcc.mytrainer.model.Treinador;
import tcc.mytrainer.util.StringUtil;

/**
 * Created by Marlon on 02/09/2017.
 */

public class Session {

    //LISTENER PARA AVISAR QUE DE CARREGAR O BANCO
    public interface FirebaseReady {
        public void listenerFirebaseReady();
    }
    static FirebaseReady firebaseReady;

    //INSTANCIAS DE AUTH E DATABASE
    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    //INTETIDADES DO BANCO
    public static Treinador treinador;

    public static void initEntitys(FirebaseReady firebaseReadyImplement) {
        firebaseReady = firebaseReadyImplement;
        initEntitys();
    }

    public static void initEntitys() {
        bindTreinador();
    }

    private static void bindTreinador() {
        //OBTEM ID DO USUARIO DA SESSÃO
        String keyTreinador = StringUtil.formatEmailToId(mAuth.getCurrentUser().getEmail());
        //CRIA ID PARA O NOVO OBJETO TREINO
        mDatabase.child("Treinador").child(keyTreinador).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                treinador = dataSnapshot.getValue(Treinador.class);
                firebaseReady.listenerFirebaseReady();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public static String getId() {
        return mDatabase.push().getKey();
    }


}
