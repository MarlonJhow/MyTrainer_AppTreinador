package tcc.mytrainer.menus.cobranca;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tcc.mytrainer.R;

/**
 * Created by Marlon on 15/09/2017.
 */

public class CobrancaActivity extends Fragment {

    private View view;
    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //LOAD CONTEXT
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.cobranca_activity, container, false);
        context = view.getContext();

        FloatingActionButton buttonAddCobranca = (FloatingActionButton) view.findViewById(R.id.buttonAddCobranca);
        buttonAddCobranca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, CadastroCobrancaActivity.class));
            }
        });

        return view;
    }
}
