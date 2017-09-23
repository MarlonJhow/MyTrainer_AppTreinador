package tcc.mytrainer.navbar.cobranca;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import tcc.mytrainer.R;
import tcc.mytrainer.database.Session;
import tcc.mytrainer.dto.AlunoDTO;
import tcc.mytrainer.util.DownloadImageTask;

/**
 * Created by Marlon on 15/09/2017.
 */

class AlunoAdapter extends RecyclerView.Adapter {

    private List<AlunoDTO> alunosDtos;
    private Context context;
    private AlunoAdapter.OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public AlunoAdapter(List<AlunoDTO> alunos, Context context, OnItemClickListener onItemClickListener) {
        this.alunosDtos = alunos;
        this.context = context;
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.cobranca_aluno_item_card, parent, false);
        AlunoHolder holder = new AlunoHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        AlunoHolder alunoHolder = (AlunoHolder) holder;
        AlunoDTO alunoDTO = alunosDtos.get(position);

        alunoHolder.getNome().setText(alunoDTO.getNome());
        alunoHolder.getEmail().setText(alunoDTO.getEmail());


        //GET IMAGE FROM URL
//            URL url = null;
//            Bitmap bmp = null;
//            url = new URL(alunoDTO.getFoto());
//            InputStream is = (InputStream) url.getContent();
//            Drawable d = Drawable.createFromStream(is, "src name");
        alunoHolder.getFoto().setImageBitmap(Session.fotosAlunos.get(alunoDTO.getFoto()));

        alunoHolder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return alunosDtos.size();
    }
}
