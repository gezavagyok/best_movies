package org.tek.geza.bestmovies.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import org.tek.geza.bestmovies.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gezacsorba on 01/12/2016.
 */
public class PosterViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.iv_poster)
    public ImageView imageView;

    public PosterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
