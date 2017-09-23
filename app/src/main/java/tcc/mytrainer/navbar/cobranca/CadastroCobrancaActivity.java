package tcc.mytrainer.navbar.cobranca;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import tcc.mytrainer.R;

/**
 * Created by Marlon on 15/09/2017.
 */

public class CadastroCobrancaActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cobranca_cadastro_activity);
        context = this;

        ImageView searchImage = (ImageView) findViewById(R.id.cadastroAlunoSearchImage);
        searchImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogAddAtividade = new ListAlunosDialog();
                dialogAddAtividade.show(getSupportFragmentManager(), "searchAluno");
            }
        });


    }
}
