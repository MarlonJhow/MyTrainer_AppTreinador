package tcc.mytrainer.fragment.treinos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import tcc.mytrainer.R;

/**
 * Created by Marlon on 04/08/2017.
 */

public class TreinoHolder extends RecyclerView.ViewHolder {

    private TextView nome;
    private TextView descricao;

    public TreinoHolder(View itemView) {
        super(itemView);
        nome = (TextView) itemView.findViewById(R.id.item_atividade_nome);
    }

    public TextView getNome() {
        return nome;
    }

    public void setNome(TextView nome) {
        this.nome = nome;
    }

    public TextView getDescricao() {
        return descricao;
    }

    public void setDescricao(TextView descricao) {
        this.descricao = descricao;
    }
}
