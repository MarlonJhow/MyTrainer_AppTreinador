package tcc.mytrainer.fragment.treinos.cadastro;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import tcc.mytrainer.R;

/**
 * Created by Marlon on 04/09/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    // TODO FIX EXERCISE 12 BIKE
    private Integer[] mThumbIds = {
            R.drawable.ic_exercise, R.drawable.ic_exercise1,
            R.drawable.ic_exercise2, R.drawable.ic_exercise3,
            R.drawable.ic_exercise4, R.drawable.ic_exercise5,
            R.drawable.ic_exercise6, R.drawable.ic_exercise7,
            R.drawable.ic_exercise8, R.drawable.ic_exercise9,
            R.drawable.ic_exercise10, R.drawable.ic_exercise11,
            R.drawable.ic_exercise12, R.drawable.ic_exercise13,
            R.drawable.ic_exercise14
    };



    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return mThumbIds[position];
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);

        return imageView;
    }

}
