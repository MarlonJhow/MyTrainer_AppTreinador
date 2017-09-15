package tcc.mytrainer.navbar.conta;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.facade.ContaFacade;
import tcc.mytrainer.model.ContaPagSeguro;

/**
 * Created by Marlon on 28/07/2017.
 */

public class ContaActivity extends AppCompatActivity implements EditContaDialog.EditContaDialogListener {

    private Context context;
    private ContaPagSeguro contaPagSeguro;


    //FIELDS
    TextView pagSeguroEmail;
    TextView pagSeguroToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.financeiro_pagseguro_activity);
        context = this;

        //INIT FIELDS
        pagSeguroEmail = (TextView) findViewById(R.id.contaEmail);
        pagSeguroToken = (TextView) findViewById(R.id.contaToken);

        //INIT CONTA BANCARIA
        contaPagSeguro = Session.treinador.getContaPagSeguro();
        if (contaPagSeguro != null) {
            atualizarCampos(contaPagSeguro.getEmail(), contaPagSeguro.getToken());
        } else {
            contaPagSeguro = new ContaPagSeguro();
        }

        //BUTTON EDITAR
        FloatingActionButton buttonEdit = (FloatingActionButton) findViewById(R.id.buttonEditConta);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (contaPagSeguro != null) {
                    bundle.putString("email", contaPagSeguro.getEmail());
                    bundle.putString("token", contaPagSeguro.getToken());
                }

                DialogFragment dialogAddAtividade = new EditContaDialog();
                dialogAddAtividade.setArguments(bundle);
                dialogAddAtividade.show(getSupportFragmentManager(), "editContaDialog");
            }
        });

        //BUTTON VOLTAR
        Button buttonVoltar = (Button) findViewById(R.id.contaVoltarButton);
        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onDialogPositiveClick(EditContaDialog dialog) {
        contaPagSeguro.setEmail(dialog.email);
        contaPagSeguro.setToken(dialog.token);

        atualizarCampos(dialog.email, dialog.token);
        ContaFacade.salvarConta(contaPagSeguro);
    }

    private void atualizarCampos(String email, String token) {
        pagSeguroEmail.setText(email);
        pagSeguroToken.setText(getEstrelinhas(token));
    }

    private String getEstrelinhas(String token) {
        if (token != null) {
            int count = 0;
            String estrelinhas = "";
            while (count < token.length()) {
                estrelinhas += "*";
                count++;
            }
            return estrelinhas;
        } else {
            return null;
        }


    }
}
