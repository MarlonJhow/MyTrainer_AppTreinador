package tcc.mytrainer.menus.cobranca;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.model.Aluno;

/**
 * Created by Marlon on 15/09/2017.
 */

public class CadastroCobrancaActivity extends AppCompatActivity implements ListAlunosDialog.ListAlunosDialogListener {

    private Context context;
    private ImageView searchImage;
    private TextView nomeAluno;
    private Aluno aluno;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cobranca_cadastro_activity);
        context = this;

        nomeAluno = (TextView) findViewById(R.id.cobrancaNomeAlunoText);

        searchImage = (ImageView) findViewById(R.id.cadastroAlunoSearchImage);
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogAddAtividade = new ListAlunosDialog();
                dialogAddAtividade.show(getSupportFragmentManager(), "searchAluno");
            }
        });


    }

    @Override
    public void getAlunoId(String idAluno) {
        aluno = Session.alunos.get(idAluno);

        searchImage.setImageBitmap(Session.fotosAlunos.get(aluno.getFotoUrl()));
        nomeAluno.setText(aluno.getNome());
    }
}
