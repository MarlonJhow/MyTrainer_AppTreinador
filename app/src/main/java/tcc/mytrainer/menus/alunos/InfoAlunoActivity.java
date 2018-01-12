package tcc.mytrainer.menus.alunos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.model.Aluno;
import tcc.mytrainer.model.Treino;

/**
 * Created by Marlon on 12/01/2018.
 */

public class InfoAlunoActivity extends AppCompatActivity implements AlunoTreinoAdapter.OnItemClickListener {

    private Context context;

    private Aluno aluno;
    private HashMap<String, Treino> treinos = new HashMap<>();

    //COMPONENTES
    ImageView alunoFoto;
    TextView alunoNome;
    TextView alunoEmail;
    FloatingActionButton buttonDeletar;
    FloatingActionButton buttonAddTreino;
    Button buttonCancelar;
    Button buttonSalvar;
    RecyclerView rvView;
    AlunoTreinoAdapter treinoAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_aluno_activity);
        context = this;

        //CARREGAR COMPONENTES
        alunoFoto = (ImageView) findViewById(R.id.alunoFoto);
        alunoNome = (TextView) findViewById(R.id.alunoCardNomeText);
        alunoEmail = (TextView) findViewById(R.id.alunoCardEmailText);
        buttonDeletar = (FloatingActionButton) findViewById(R.id.buttonDeletarAluno);
        buttonAddTreino = (FloatingActionButton) findViewById(R.id.buttonAddTreino);
        buttonCancelar = (Button) findViewById(R.id.buttonVoltar);
        buttonSalvar = (Button) findViewById(R.id.buttonSalvar);

        //CARREGA PERFIL DO ALUNO
        String idAluno = getIntent().getStringExtra("ID_ALUNO");
        aluno = Session.alunos.get(idAluno);
        Picasso.with(context).load(aluno.getFotoUrl()).into(alunoFoto);
        alunoNome.setText(aluno.getNome());
        alunoEmail.setText(aluno.getEmail());

        //CARREGAR TREINOS
        for (String idTreino : aluno.getIdTreinos().values()) {
            Task task = Session.getTreino(idTreino);
            task.addOnCompleteListener(new OnCompleteListener<Treino>() {
                @Override
                public void onComplete(@NonNull Task<Treino> task) {
                    addTreinoAndInitRecyclerView(task.getResult());
                }
            });
        }

    }

    private void addTreinoAndInitRecyclerView(Treino treino) {
        treinos.put(treino.getId(), treino);
        if (treinos.size() == aluno.getIdTreinos().size()) {
            //INIT Recycler View
            rvView = (RecyclerView) findViewById(R.id.rv_treinos);
            treinoAdapter = new AlunoTreinoAdapter(treinos, this, this);
            rvView.setAdapter(treinoAdapter);
            RecyclerView.LayoutManager layout = new LinearLayoutManager(context,
                    LinearLayoutManager.VERTICAL, false);
            rvView.setLayoutManager(layout);
        }

    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
