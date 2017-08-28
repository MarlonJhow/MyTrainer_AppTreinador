package tcc.mytrainer.fragment.treinos.cadastro;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import tcc.mytrainer.R;
import tcc.mytrainer.model.Atividade;

/**
 * Created by Marlon on 27/08/2017.
 */

public class AtividadeAdapter extends RecyclerView.Adapter {

    List<Atividade> atividades;
    private Context context;

    public AtividadeAdapter(List<Atividade> atividades, Context context) {
        this.atividades = atividades;
        this.context = context;
    }
    public AtividadeAdapter(HashMap<String, Atividade> atividades, Context context) {
        this.atividades = new ArrayList<Atividade>(atividades.values());
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.treino_atividade_item_card, parent, false);
        AtividadeHolder holder = new AtividadeHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AtividadeHolder atividadeHolder = (AtividadeHolder) holder;
        Atividade atividade = atividades.get(position);
        atividadeHolder.update(atividade);
    }

    @Override
    public int getItemCount() {
        return atividades.size();
    }

    public void updateAtividadaes(HashMap<String, Atividade> atividades) {
        this.atividades = new ArrayList<Atividade>(atividades.values());
    }
}
