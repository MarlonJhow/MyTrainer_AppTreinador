package tcc.mytrainer.fragment.treinos;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import tcc.mytrainer.model.Treino;

/**
 * Created by Marlon on 03/08/2017.
 */

class TreinoAdapter extends RecyclerView.Adapter {

    List<Treino> treinos;

    public TreinoAdapter(List<Treino> treinos) {
        this.treinos = treinos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
