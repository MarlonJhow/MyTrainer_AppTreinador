package tcc.mytrainer.fragment.treinos;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.facade.TreinoFacade;
import tcc.mytrainer.fragment.treinos.cadastro.CadastroTreino;
import tcc.mytrainer.model.Treino;
import tcc.mytrainer.web.WebCliente;

/**
 * Created by Marlon on 28/07/2017.
 */

public class TreinosFragment extends Fragment implements TreinoAdapter.OnItemClickListener {

    View view;
    RecyclerView rvView;
    TreinoAdapter treinoAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //CREATE VIEW
        view = inflater.inflate(R.layout.treino_fragment, container, false);

        //CREATE TREINO
        final FloatingActionButton buttonAddTreino = (FloatingActionButton) view.findViewById(R.id.buttonAddTreino);
        buttonAddTreino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CadastroTreino.class));
            }
        });

        //INIT Recycler View
        rvView = (RecyclerView) view.findViewById(R.id.rv_treinos);
        treinoAdapter = new TreinoAdapter(new ArrayList<Treino>(Session.treinador.getTreinos().values()), getActivity(), this);
        rvView.setAdapter(treinoAdapter);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rvView.setLayoutManager(layout);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        treinoAdapter.update();
        treinoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onItemClick(View view, final int position) {
        //PEGA O ID DO TREINO
        final String treinoId = treinoAdapter.treinos.get(position).getId();

        //CRIA MENU AO CLICAR BOTÃO
        PopupMenu popup = new PopupMenu(getActivity(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.card_treino_cations, popup.getMenu());
        popup.show();

        //DEFINE AÇÕES DO MENU
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.treinoActionEdit:
                        Intent intent = new Intent(getActivity(), CadastroTreino.class);
                        intent.putExtra("treinoId", treinoId);
                        startActivity(intent);
                        return true;
                    case R.id.treinoActionExcluir:
                        //DIALOG CONFIRMAR EXCLUSÃO
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Tem certezar que deseja excluir?")
                                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        TreinoFacade.excluirTreino(treinoId);
                                        treinoAdapter.treinos.remove(position);
                                        treinoAdapter.notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                });

                        builder.create();
                        builder.show();

                        return true;
                    default:
                        return false;
                }
            }
        });

    }

}
