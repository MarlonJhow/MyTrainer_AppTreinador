package tcc.mytrainer.fragment.treinos;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import tcc.mytrainer.R;
import tcc.mytrainer.fragment.treinos.cadastro.CadastroTreino;
import tcc.mytrainer.model.Treino;
import tcc.mytrainer.web.WebCliente;

/**
 * Created by Marlon on 28/07/2017.
 */

public class TreinosFragment extends Fragment {

    View view;
    RecyclerView rvView;
    List<Treino> treinos = new ArrayList<>();

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
        buttonAddTreino.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CadastroTreino.class));
            }
        });

        //GET TREINOS
        treinos = getTreinos();

        //INIT Recycler View
        rvView = (RecyclerView) view.findViewById(R.id.rv_treinos);
        rvView.setAdapter(new TreinoAdapter(treinos, getActivity()));
        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        rvView.setLayoutManager(layout);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public List<Treino> getTreinos() {
        try {
            WebCliente web = new WebCliente();
            String jsonString = null;
            jsonString = web.execute(new String[]{"treino"}).get();
            return Treino.toList(new JSONArray(jsonString));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
