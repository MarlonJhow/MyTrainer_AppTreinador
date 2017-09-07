package tcc.mytrainer.fragment.conta;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import tcc.mytrainer.R;
import tcc.mytrainer.util.StringUtil;

/**
 * Created by Marlon on 07/09/2017.
 */

public class EditContaDialog extends DialogFragment {

    //STRINGS
    public String nome;
    public String cpf;
    public String agencia;
    public String conta;
    public String digito;

    //FIELDS
    EditText textNome;
    EditText textCpf;
    EditText textAgencia;
    EditText textConta;
    EditText textDigito;

    View view;

    EditContaDialogListener mListener;

    public interface EditContaDialogListener {
        public void onDialogPositiveClick(EditContaDialog dialog);
    }

    @Override
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.financeiro_conta_edit_dialog, null);

        //INIT FIELDS
        textNome = (EditText) view.findViewById(R.id.contaNomeDialog);
        textCpf = (EditText) view.findViewById(R.id.contaCpfDialog);
        textAgencia = (EditText) view.findViewById(R.id.contaAgenciaDialog);
        textConta = (EditText) view.findViewById(R.id.contaContaDialog);
        textDigito = (EditText) view.findViewById(R.id.contaDigitoDialog);

        if(getArguments() != null){
            textNome.setText(getArguments().getString("nome"));
            textCpf.setText(getArguments().getString("cpf"));
            textAgencia.setText(getArguments().getString("agencia"));
            textConta.setText(getArguments().getString("conta"));
            textDigito.setText(getArguments().getString("digito"));
        }

        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        nome = textNome.getText().toString();
                        cpf = textCpf.getText().toString();
                        agencia = textAgencia.getText().toString();
                        conta = textConta.getText().toString();
                        digito = textDigito.getText().toString();


                        if (validarCampos(nome, cpf, agencia, conta, digito)) {
                            mListener.onDialogPositiveClick(EditContaDialog.this);
                        }

                    }

                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EditContaDialog.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (EditContaDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    private boolean validarCampos(String nome, String cpf, String agencia, String conta, String digito) {
        boolean validate = true;

        //SE ACHAR DIGITO NUMERICO
        if (Pattern.compile("\\d").matcher(nome).find()) {
            Toast.makeText(getActivity(), "O NOME DEVE CONTER APENAS LETRAS", Toast.LENGTH_LONG).show();
            validate = false;
        }
        if (Pattern.compile("\\D").matcher(cpf).find()) {
            Toast.makeText(getActivity(), "O CPF DEVE CONTER APENAS NUMEROS", Toast.LENGTH_LONG).show();
            validate = false;
        }
        if (cpf.length() != 11) {
            Toast.makeText(getActivity(), "O CPF DEVE CONTER 11 DIGITOS", Toast.LENGTH_LONG).show();
            validate = false;
        }
        if (Pattern.compile("\\D").matcher(agencia).find()) {
            Toast.makeText(getActivity(), "A AGENCIA DEVE CONTER APENAS DIGITOS", Toast.LENGTH_LONG).show();
            validate = false;
        }
        if (Pattern.compile("\\D").matcher(agencia).find()) {
            Toast.makeText(getActivity(), "O DIGITO DEVE SER NUMERICO ", Toast.LENGTH_LONG).show();
            validate = false;
        }
        if (digito.length() != 1) {
            Toast.makeText(getActivity(), "O DIGITO DEVE CONTER APENAS 1 DIGITO", Toast.LENGTH_LONG).show();
            validate = false;
        }
        if (nome.isEmpty() || cpf.isEmpty() || agencia.isEmpty() || conta.isEmpty() || digito.isEmpty()) {
            Toast.makeText(getActivity(), "TODOS OS CAMPOS DEVEM SER PREENCHIDOS", Toast.LENGTH_LONG).show();
            validate = false;
        }

        if (validate) {
            Toast.makeText(getActivity(), "DADOS DA CONTA SALVO COM SUCESSO", Toast.LENGTH_LONG).show();
        }

        return validate;
    }


}
