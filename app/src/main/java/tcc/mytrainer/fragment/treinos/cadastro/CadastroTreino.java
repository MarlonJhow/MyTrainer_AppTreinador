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
import tcc.mytrainer.database.Session;
import tcc.mytrainer.facade.TreinoFacade;
import tcc.mytrainer.model.Atividade;
import tcc.mytrainer.model.Treinador;
import tcc.mytrainer.model.Treino;
import tcc.mytrainer.util.StringUtil;

public class CadastroTreino extends AppCompatActivity implements DialogCadastroTreino.CadastroTreinoDialogListener {

    private RecyclerView rvAtividades;
    private Treino treino;
    private Context context;
    private AtividadeAdapter atividadeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.treino_cadastro_activity);
        context = this;

        //CREATE TREINO
        treino = new Treino();

        //CREATE RecyclerVIew
        rvAtividades = (RecyclerView) findViewById(R.id.rvAtividades);
        atividadeAdapter = new AtividadeAdapter(treino.getAtividades(), this);
        rvAtividades.setAdapter(atividadeAdapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        rvAtividades.setLayoutManager(layout);


        //BUTTON SHOW DIALOG ADD ATIVIDADE
        Button buttonAddAtividade = (Button) findViewById(R.id.buttonAddAtividade);
        buttonAddAtividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogAddAtividade = new DialogCadastroTreino();
                dialogAddAtividade.show(getSupportFragmentManager(), "dialogAddAtividade");
            }
        });

        //BUTTON SALVAR
        Button buttonSalvar = (Button) findViewById(R.id.buttonCadastroTreinoSalvar);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = ((EditText) findViewById(R.id.treinoNome)).getText().toString();
                String descricao = ((EditText) findViewById(R.id.treinoDescricao)).getText().toString();

                treino.setNome(nome);
                treino.setDescricao(descricao);

                TreinoFacade.salvarTreino(treino);
                finish();
            }
        });

        //BUTTON CANCELAR
        Button buttonCancelar = (Button) findViewById(R.id.buttonCadastroTreinoCancelar);
        buttonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onDialogPositiveClick(DialogCadastroTreino dialog) {

        if (treino.getAtividades().get(dialog.atividadeNome.getText().toString()) == null) {
            Atividade atividade = new Atividade(dialog.atividadeNome.getText().toString(), dialog.atividadeDescricao.getText().toString(), dialog.atividadeRepeticoes.getText().toString(), dialog.atividadeSeries.getText().toString());
            atividade.setId(Session.getId());
            treino.getAtividades().put(atividade.getId(), atividade);
            atividadeAdapter.updateAtividadaes(treino.getAtividades());
            atividadeAdapter.notifyDataSetChanged();
        } else {
            //TODO TOAST MESSAGE NOME REPETIDO
        }
    }

}
