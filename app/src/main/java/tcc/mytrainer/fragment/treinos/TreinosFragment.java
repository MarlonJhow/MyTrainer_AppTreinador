package tcc.mytrainer.fragment.treinos;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import tcc.mytrainer.R;
import tcc.mytrainer.model.Treino;
import tcc.mytrainer.web.WebCliente;

/**
 * Created by Marlon on 28/07/2017.
 */

public class TreinosFragment extends Fragment {

    View view;
    RecyclerView rvView;
    List<Treino> treinos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        treinos = getTreinos();
        rvView = (RecyclerView) getView().findViewById(R.id.rv_treinos);

        rvView.setAdapter(new TreinoAdapter(treinos, getActivity()));

        RecyclerView.LayoutManager layout = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);

        rvView.setLayoutManager(layout);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_treinos, container, false);
        return view;
    }

    public List<Treino> getTreinos() {
        try {
            WebCliente web = new WebCliente();
            String response = web.execute(new String[] {"treino"}).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new ArrayList<Treino>();
    }
}
