package tcc.mytrainer.fragment.conta;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.facade.ContaFacade;
import tcc.mytrainer.model.ContaBancaria;

/**
 * Created by Marlon on 28/07/2017.
 */

public class ContaActivity extends AppCompatActivity implements EditContaDialog.EditContaDialogListener {

    private Context context;
    private ContaBancaria contaBancaria;


    //FIELDS
    TextView contaNome;
    TextView contaCpf;
    TextView contaAgencia;
    TextView contaConta;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.financeiro_conta_activity);
        context = this;

        //INIT FIELDS
        contaNome = (TextView) findViewById(R.id.contaNomeText);
        contaCpf = (TextView) findViewById(R.id.contaCpfText);
        contaAgencia = (TextView) findViewById(R.id.contaAgenciaText);
        contaConta = (TextView) findViewById(R.id.contaContaText);

        //INIT CONTA BANCARIA
        contaBancaria = Session.treinador.getContaBancaria();
        if(contaBancaria != null){
            atualizarCampos(contaBancaria.getNomeTitular(), contaBancaria.getCpf(), contaBancaria.getAgencia(), contaBancaria.getConta(), contaBancaria.getContaDigito());
        } else {
            contaBancaria = new ContaBancaria();
        }

        //BUTTON EDITAR
        FloatingActionButton buttonEdit = (FloatingActionButton) findViewById(R.id.buttonEditConta);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if(contaBancaria != null){
                    bundle.putString("nome", contaBancaria.getNomeTitular());
                    bundle.putString("cpf", contaBancaria.getCpf());
                    bundle.putString("agencia", contaBancaria.getAgencia());
                    bundle.putString("conta", contaBancaria.getConta());
                    bundle.putString("digito", contaBancaria.getContaDigito());
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
        contaBancaria.setNomeTitular(dialog.nome);
        contaBancaria.setCpf(dialog.cpf);
        contaBancaria.setAgencia(dialog.agencia);
        contaBancaria.setConta(dialog.conta);
        contaBancaria.setContaDigito(dialog.digito);

        atualizarCampos(dialog.nome, dialog.cpf, dialog.agencia, dialog.conta, dialog.digito);
        ContaFacade.salvarConta(contaBancaria);
    }

    private void atualizarCampos(String nome, String cpf, String agencia, String conta, String digito){
        contaNome.setText(nome);
        contaCpf.setText(cpf);
        contaAgencia.setText(agencia);
        String contaComDigito = conta + " - " + digito;
        contaConta.setText(contaComDigito);
    }
}
