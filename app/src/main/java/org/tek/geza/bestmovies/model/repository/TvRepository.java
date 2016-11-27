package org.tek.geza.bestmovies.model.repository;

import org.tek.geza.bestmovies.model.MovieDbApi;
import org.tek.geza.bestmovies.model.tv.detail.TvShowDetail;
import org.tek.geza.bestmovies.model.tv.list.TvShow;
import org.tek.geza.bestmovies.util.transformer.TvShowTransformer;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TvRepository {

    MovieDbApi api;

    TvShowTransformer tvShowTransformer;

    public TvRepository(MovieDbApi api, TvShowTransformer tvShowTransformer) {
        this.api = api;
        this.tvShowTransformer = tvShowTransformer;
    }

    public Observable<TvShow> get20TopTvShows() {
        return api.getPopularTvShows()
                .flatMap(tvShowTransformer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<TvShow> searchTopTvShows(String query) {
        return api.searchTvShows(query)
                .flatMap(tvShowTransformer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<TvShowDetail> getTvShowDetail(int id) {
        return api.getTvShowDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
