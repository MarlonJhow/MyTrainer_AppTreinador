package tcc.mytrainer.menus.cobranca;

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

public class CobrancaActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cobranca_activity);
        context = this;

        FloatingActionButton buttonAddCobranca = (FloatingActionButton) findViewById(R.id.buttonAddCobranca);
        buttonAddCobranca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, CadastroCobrancaActivity.class));
            }
        });

    }
}
