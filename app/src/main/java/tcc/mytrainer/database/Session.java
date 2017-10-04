package tcc.mytrainer.database;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import tcc.mytrainer.dto.AlunoDTO;
import tcc.mytrainer.model.Aluno;
import tcc.mytrainer.model.Treinador;
import tcc.mytrainer.util.DownloadImageMapTask;
import tcc.mytrainer.util.DownloadImageTask;
import tcc.mytrainer.util.StringUtil;

/**
 * Created by Marlon on 02/09/2017.
 */

public class Session {

    //INSTANCIAS DE AUTH E DATABASE
    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    //INTETIDADES DO BANCO
    public static Treinador treinador;
    public static HashMap<String, Aluno> alunos = new HashMap<String, Aluno>();
//    public static HashMap<String, Bitmap> fotosAlunos = new HashMap<String, Bitmap>();

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
                bindAlunos();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private static void bindAlunos() {
        if(treinador.getIdAlunos() != null && treinador.getIdAlunos().size() > 0){
            for (String idAluno : treinador.getIdAlunos().values()) {
                mDatabase.child("Aluno").child(idAluno).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Aluno aluno = dataSnapshot.getValue(Aluno.class);
                        alunos.put(aluno.getId(), aluno);
//                        new DownloadImageMapTask(fotosAlunos).execute(aluno.getFotoUrl());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }
    }

    public static String getId() {
        return mDatabase.push().getKey();
    }


    public static void saveTreinador(){

    }


}
