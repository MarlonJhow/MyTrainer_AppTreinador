package tcc.mytrainer.menus.alunos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.menus.treinos.TreinoHolder;
import tcc.mytrainer.model.Treino;

/**
 * Created by Marlon on 12/01/2018.
 */

public class AlunoTreinoAdapter extends RecyclerView.Adapter {

public List<Treino> treinos;
private Context context;
private OnItemClickListener mOnItemClickListener;

public interface OnItemClickListener{
    void onItemClick(View view, int position);
}

    public AlunoTreinoAdapter(Map<String, Treino> treinos, Context context, AlunoTreinoAdapter.OnItemClickListener mOnItemClickListener) {
        this.treinos = new ArrayList<Treino>(treinos.values());
        this.context = context;
        this.mOnItemClickListener= (OnItemClickListener) mOnItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.alunos_treino_item_card, parent, false);
        TreinoHolder holder = new TreinoHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        TreinoHolder treinoHolder = (TreinoHolder) holder;
        Treino treino = treinos.get(position);

        treinoHolder.getNome().setText(treino.getNome());
        treinoHolder.getnAtividades().setText(Integer.toString(treino.getAtividades().size()));
        treinoHolder.getImageTreino().setImageResource(treino.getImageTreino().getDrawable());

        treinoHolder.getButtonEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return treinos.size();
    }

    public void update() {
        treinos = new ArrayList<Treino>(Session.treinos.values());
    }
}