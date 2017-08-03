package tcc.mytrainer.fragment.mensagens;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tcc.mytrainer.R;

/**
 * Created by Marlon on 28/07/2017.
 */

public class MensagensFragment extends Fragment {

    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mensagens, container, false);
        return view;
    }
}
