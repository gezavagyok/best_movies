package org.tek.geza.bestmovies.view.adapter.star;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import org.tek.geza.bestmovies.R;
import org.tek.geza.bestmovies.model.people.Person;

import java.util.ArrayList;
import java.util.List;

public class StarAdapter extends RecyclerView.Adapter<StarViewHolder> {
    private static final String PATH_BASE = "http://image.tmdb.org/t/p/w300/";

    List<Person> data;

    public StarAdapter() {
        data = new ArrayList<>(20);
    }

    @Override
    public StarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_star, parent, false);
        StarViewHolder holder = new StarViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(StarViewHolder holder, int position) {
        final Person person = data.get(position);
        if (Person.ERROR == person.getId()) {
            return;
        }
        holder.tvName.setText(person.getName());
        holder.tvMovies.setText(String.format(holder.itemView.getContext().getString(R.string.movies_schema), person.getMovies()));
        Picasso.with(holder.itemView.getContext())
                .load(PATH_BASE + person.getProfilePath())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder_error)
                .into(holder.ivPosterImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addStar(Person person) {
        data.add(person);
    }

    public void clear() {
        data.clear();
    }
}
