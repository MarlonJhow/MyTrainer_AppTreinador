package tcc.mytrainer.navbar.cobranca;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import tcc.mytrainer.R;

/**
 * Created by Marlon on 15/09/2017.
 */

public class CadastroCobrancaActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.financeiro_cobranca_cadastro_activity);
        context = this;
    }
}
