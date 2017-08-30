package tcc.mytrainer.fragment.treinos.cadastro;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.EditText;

import tcc.mytrainer.R;

/**
 * Created by Marlon on 27/08/2017.
 */

public class DialogCadastroTreino extends DialogFragment {

    CadastroTreinoDialogListener mListener;

    public interface CadastroTreinoDialogListener {
        public void onDialogPositiveClick(DialogCadastroTreino dialog);
    }

    public EditText atividadeNome;
    public EditText atividadeDescricao;
    public EditText atividadeRepeticoes;
    public EditText atividadeSeries;

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.treino_dialog_cadastro, null))
                // Add action buttons
                .setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        atividadeNome = (EditText) getDialog().findViewById(R.id.atividadeNome);
                        atividadeDescricao = (EditText) getDialog().findViewById(R.id.atividadeDescricao);
                        atividadeRepeticoes = (EditText) getDialog().findViewById(R.id.atividadeRepeticoes);
                        atividadeSeries = (EditText) getDialog().findViewById(R.id.atividadeSeries);

                        mListener.onDialogPositiveClick(DialogCadastroTreino.this);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DialogCadastroTreino.this.getDialog().cancel();
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
            mListener = (CadastroTreinoDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

}
