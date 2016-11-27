package org.tek.geza.bestmovies.presenter;

import org.tek.geza.bestmovies.model.tv.detail.TvShowDetail;
import org.tek.geza.bestmovies.model.tv.list.TvShow;
import org.tek.geza.bestmovies.presenter.usecase.load.GetTvShowDetail;
import org.tek.geza.bestmovies.presenter.usecase.load.GetTvShows;
import org.tek.geza.bestmovies.presenter.usecase.search.SearchForTvShow;

import rx.Observable;

public class TvShowPresenter {

    GetTvShows getTvShows;
    GetTvShowDetail getTvShowDetail;
    SearchForTvShow searchForTvShow;

    public TvShowPresenter(GetTvShows getTvShows, GetTvShowDetail getTvShowDetail, SearchForTvShow searchForTvShow) {
        this.getTvShows = getTvShows;
        this.getTvShowDetail = getTvShowDetail;
        this.searchForTvShow = searchForTvShow;
    }

    public Observable<TvShow> refreshList() {
        return getTvShows.execute(null);
    }

    public Observable<TvShowDetail> getDetails(int id) {
        return getTvShowDetail.execute(id);
    }

    public Observable<TvShow> searchTvShow(String query) {
        return searchForTvShow.execute(query);
    }
}
