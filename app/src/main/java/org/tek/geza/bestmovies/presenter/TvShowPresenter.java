package org.tek.geza.bestmovies.presenter;

import org.tek.geza.bestmovies.model.tv.list.TvShow;
import org.tek.geza.bestmovies.presenter.usecase.load.GetTvShows;
import org.tek.geza.bestmovies.presenter.usecase.search.SearchForTvShow;

import rx.Observable;

public class TvShowPresenter {

    GetTvShows getTvShows;
    SearchForTvShow searchForTvShow;

    public TvShowPresenter(GetTvShows getTvShows, SearchForTvShow searchForTvShow) {
        this.getTvShows = getTvShows;
        this.searchForTvShow = searchForTvShow;
    }

    public Observable<TvShow> refreshList() {
        return getTvShows.execute(null);
    }

    public Observable<TvShow> searchTvShow(String query) {
        return searchForTvShow.execute(query);
    }
}
