package org.tek.geza.bestmovies.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import org.tek.geza.bestmovies.R;
import org.tek.geza.bestmovies.model.movie.response.image.Poster;

import java.util.List;

/**
 * Created by gezacsorba on 01/12/2016.
 */

public class MoviePosterAdapter extends RecyclerView.Adapter<PosterViewHolder> {

    List<Poster> posters;

    public MoviePosterAdapter(List<Poster> imageUrls) {
        posters = imageUrls;
    }

    @Override
    public PosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_poster,parent,false);
        return new PosterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PosterViewHolder holder, int position) {
        Picasso.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500"+posters.get(position).getFilePath()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return posters.size() < 6? posters.size() : 6;
    }
}
