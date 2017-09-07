package tcc.mytrainer.fragment.conta;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
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

        if (getArguments() != null) {
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
    public void onStart() {
        super.onStart();
        AlertDialog d = (AlertDialog) getDialog();
        if (d != null) {
            Button positiveButton = (Button) d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nome = textNome.getText().toString();
                    cpf = textCpf.getText().toString();
                    agencia = textAgencia.getText().toString();
                    conta = textConta.getText().toString();
                    digito = textDigito.getText().toString();


                    if (validarCampos(nome, cpf, agencia, conta, digito)) {
                        mListener.onDialogPositiveClick(EditContaDialog.this);
                        dismiss();
                    }

                }
            });
        }
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
            toastError("O NOME DEVE CONTER APENAS LETRAS");
            validate = false;
        }
        if (cpf.length() != 11) {
            toastError("O CPF DEVE CONTER 11 DIGITOS");
            validate = false;
        }
        if (nome.isEmpty() || cpf.isEmpty() || agencia.isEmpty() || conta.isEmpty() || digito.isEmpty()) {
            toastError("TODOS OS CAMPOS DEVEM SER PREENCHIDOS");
            validate = false;
        }

        if (validate) {
            Toast.makeText(getActivity(), "DADOS DA CONTA SALVO COM SUCESSO", Toast.LENGTH_LONG).show();
        }

        return validate;
    }

    private void toastError(String textError) {
        Toast.makeText(getActivity(), textError, Toast.LENGTH_LONG).show();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getDialog().getCurrentFocus().getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }


}
