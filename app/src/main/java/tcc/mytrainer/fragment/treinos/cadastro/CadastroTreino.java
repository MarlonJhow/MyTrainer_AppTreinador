package tcc.mytrainer.fragment.treinos.cadastro;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import tcc.mytrainer.R;
import tcc.mytrainer.model.Atividade;
import tcc.mytrainer.model.Treinador;
import tcc.mytrainer.model.Treino;
import tcc.mytrainer.util.StringUtil;

public class CadastroTreino extends AppCompatActivity implements DialogCadastroTreino.CadastroTreinoDialogListener {

    private RecyclerView rvAtividades;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private Treino treino;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.treino_cadastro_activity);
        context = this;

        //CREATE TREINO
        treino = new Treino();

        //CREATE RecyclerVIew
        Atividade atividade = new Atividade("nome", "descricao", 5, 5);
        treino.getAtividades().put(atividade.getNome(), atividade);
        rvAtividades = (RecyclerView) findViewById(R.id.rvAtividades);
        final AtividadeAdapter atividadeAdapter = new AtividadeAdapter(treino.getAtividades(), this);
        rvAtividades.setAdapter(atividadeAdapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        rvAtividades.setLayoutManager(layout);


        //SHOW DIALOG ADD ATIVIDADE
        Button buttonAddAtividade = (Button) findViewById(R.id.buttonAddAtividade);
        buttonAddAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogAddAtividade = new DialogCadastroTreino();
                dialogAddAtividade.show(getSupportFragmentManager(), "dialogAddAtividade");
            }
        });
//        buttonAddAtividade.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditText nomeAtividade = (EditText) findViewById(R.id.nomeAtividade);
//                EditText descricaoAtividade = (EditText) findViewById(R.id.descricaoAtividade);
//                EditText nRepeticoes = (EditText) findViewById(R.id.nRepeticoes);
//                EditText nSeries = (EditText) findViewById(R.id.nSeries);
//
//                if(treino.getAtividades().get(nomeAtividade) == null){
//                    Atividade atividade = new Atividade(nomeAtividade.getText().toString(), descricaoAtividade.getText().toString(), nRepeticoes.getText().toString(), nSeries.getText().toString());
//                    treino.getAtividades().put(atividade.getNome(), atividade);
//                    atividadeAdapter.updateAtividadaes(treino.getAtividades());
//                    atividadeAdapter.notifyDataSetChanged();
//                } else {
//                    //TODO TOAST MESSAGE NOME REPETIDO
//                }
//
//            }
//        });


    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // User touched the dialog's positive button
        ...
    }

}
