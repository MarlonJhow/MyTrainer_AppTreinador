package tcc.mytrainer.fragment.treinos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import tcc.mytrainer.R;
import tcc.mytrainer.model.Treino;

/**
 * Created by Marlon on 03/08/2017.
 */

class TreinoAdapter extends RecyclerView.Adapter {

    List<Treino> treinos;
    private Context context;

    public TreinoAdapter(List<Treino> treinos, Context context) {
        this.treinos = treinos;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.treino_item, parent, false);
        TreinoHolder holder = new TreinoHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TreinoHolder treinoHolder = (TreinoHolder) holder;
        Treino treino = treinos.get(position);
        treinoHolder.getNome().setText(treino.getNome());
    }

    @Override
    public int getItemCount() {
        return treinos.size();
    }
}
