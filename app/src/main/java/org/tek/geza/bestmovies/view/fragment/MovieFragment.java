package org.tek.geza.bestmovies.view.fragment;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.tek.geza.bestmovies.BestMoviesApplication;
import org.tek.geza.bestmovies.di.module.ui.MovieModule;
import org.tek.geza.bestmovies.model.movie.list.Movie;
import org.tek.geza.bestmovies.presenter.MoviePresenter;
import org.tek.geza.bestmovies.util.event.LoadRequestEvent;
import org.tek.geza.bestmovies.util.event.SearchRequestEvent;
import org.tek.geza.bestmovies.util.network.ErrorHandler;
import org.tek.geza.bestmovies.view.activity.HomeActivity;
import org.tek.geza.bestmovies.view.adapter.movie.MovieAdapter;

import java.util.Map;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

public class MovieFragment extends ContentListFragment {

    MovieAdapter movieAdapter;

    @Inject
    MoviePresenter presenter;

    @Inject
    LinearLayoutManager layoutManager;

    @Inject
    ErrorHandler errorHandler;

    void onInit(){
        movieAdapter = new MovieAdapter((AppCompatActivity) getActivity());
    }

    @Override
    protected void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(movieAdapter);
    }

    @Subscribe
    public void onLoadRequest(LoadRequestEvent event) {
        if (getUserVisibleHint()) {
            movieAdapter.clear();
            onRefresh();
        }
    }

    @Subscribe
    public void onSearchRequest(SearchRequestEvent event) {
        if (!getUserVisibleHint()) return;

        Map hits = new HitBuilders.EventBuilder()
                .setAction("movie search")
                .setCategory("search")
                .setLabel(event.getQuery().toString())
                .build();

        Tracker tracker = BestMoviesApplication.get().getDefaultTracker();
        tracker.send(hits);

        movieAdapter.clear();
        Subscription s = presenter.searchMovie(event.getQuery().toString())
                .doOnNext(new Action1<Movie>() {
                    @Override
                    public void call(Movie movie) {
                        movieAdapter.addMovie(movie);
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        movieAdapter.notifyDataSetChanged();
                    }
                })
                .onErrorReturn(new Func1<Throwable, Movie>() {
                    @Override
                    public Movie call(Throwable throwable) {
                        errorHandler.onError(throwable);
                        return Movie.error();
                    }
                })
                .subscribe();
        subscription.add(s);
    }


    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        onRefresh();
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    public void onRefresh() {
        Subscription s = presenter.refreshList()
                .doOnNext(new Action1<Movie>() {
                    @Override
                    public void call(Movie movie) {
                        movieAdapter.addMovie(movie);
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        movieAdapter.notifyDataSetChanged();
                    }
                }).onErrorReturn(new Func1<Throwable, Movie>() {
                    @Override
                    public Movie call(Throwable throwable) {
                        errorHandler.onError(throwable);
                        return Movie.error();
                    }
                }).subscribe();
        subscription.add(s);
    }

    @Override
    protected void inject() {
        BestMoviesApplication.getComponent()
                .movie(((HomeActivity)getActivity()).getActivityModule(),
                        new MovieModule())
                .inject(this);
    }

    @Override
    void sendAnalytics() {
        Tracker tracker = BestMoviesApplication.get().getDefaultTracker();
        tracker.setScreenName(ContentListFragment.MOVIE_TITLE.toString());
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }


}
