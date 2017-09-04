package tcc.mytrainer.fragment.treinos.cadastro;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.facade.TreinoFacade;
import tcc.mytrainer.model.Atividade;
import tcc.mytrainer.model.Treino;

public class CadastroTreino extends AppCompatActivity implements DialogCadastroTreino.CadastroTreinoDialogListener, AtividadeAdapter.OnItemClickListener, DialogSelectImage.DialogSelectImageListener {

    private RecyclerView rvAtividades;
    private Treino treino;
    private Context context;
    private AtividadeAdapter atividadeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.treino_cadastro_activity);
        context = this;

        //INIT TREINO
        if (getIntent().getStringExtra("treinoId") != null) {
            treino = Session.treinador.getTreinos().get(getIntent().getStringExtra("treinoId"));

            EditText nomeTreino = (EditText) findViewById(R.id.treinoNome);
            EditText descricaoTreino = (EditText) findViewById(R.id.treinoDescricao);

            nomeTreino.setText(treino.getNome());
            descricaoTreino.setText(treino.getDescricao());
        } else {
            treino = new Treino();
        }

        //CREATE RecyclerVIew
        rvAtividades = (RecyclerView) findViewById(R.id.rvAtividades);
        atividadeAdapter = new AtividadeAdapter(treino.getAtividades(), this, this);
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

        //IMAGE CLICK
        ImageView imageView = (ImageView) findViewById(R.id.imageTreino);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogAddAtividade = new DialogSelectImage();
                dialogAddAtividade.show(getSupportFragmentManager(), "dialogSelectImage");
            }
        });

    }

    //CALLBACK BOTÃO SALVAR DO DIALOG
    @Override
    public void onDialogPositiveClick(DialogCadastroTreino dialog) {

        //ADICIONAR ATIVIDADE
        if (treino.getAtividades().get(dialog.atividadeNome.getText().toString()) == null) {
            Atividade atividade = new Atividade(dialog.atividadeNome.getText().toString(), dialog.atividadeDescricao.getText().toString(), dialog.atividadeRepeticoes.getText().toString(), dialog.atividadeSeries.getText().toString());

            if(dialog.idAtividade != null){
                atividade.setId(dialog.idAtividade);
            } else {
                atividade.setId(Session.getId());
            }
            treino.getAtividades().put(atividade.getId(), atividade);

            //ATUALIZA RECYCLER VIEW
            atividadeAdapter.updateAtividadaes(treino.getAtividades());
            atividadeAdapter.notifyDataSetChanged();
        } else {
            //TODO TOAST MESSAGE NOME REPETIDO
        }
    }

    @Override
    public void onItemClick(View view, final int position) {
        //CRIA MENU
        PopupMenu popup = new PopupMenu(this, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.card_treino_cations, popup.getMenu());
        popup.show();

        //DEFINE AÇÕES DO MENU
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.treinoActionEdit:
                        //CARREGA DADOS PARA EDIÇÃO
                        Atividade atividade = atividadeAdapter.atividades.get(position);
                        Bundle bundle = new Bundle();
                        bundle.putString("atividadeId", atividade.getId());
                        bundle.putString("atividadeNome", atividade.getNome());
                        bundle.putString("atividadeDescricao", atividade.getDescricao());
                        bundle.putString("atividadeRepeticoes", Integer.toString(atividade.getRepeticoes()));
                        bundle.putString("atividadeSeries", Integer.toString(atividade.getSeries()));

                        DialogFragment dialogAddAtividade = new DialogCadastroTreino();
                        dialogAddAtividade.setArguments(bundle);

                        dialogAddAtividade.show(getSupportFragmentManager(), "dialogAddAtividade");
                        return true;
                    case R.id.treinoActionExcluir:
                        //REMOVE ATIVIDADE DA LISTA E ATUALIZA ADAPTER
                        atividadeAdapter.atividades.remove(position);
                        atividadeAdapter.notifyDataSetChanged();
                        return true;
                    default:
                        return false;
                }
            }
        });

    }

    @Override
    public void onDialogPositiveClickSelectImage(DialogSelectImage dialog) {

        System.out.println();

    }
}
