package org.tek.geza.bestmovies.presenter;

import org.tek.geza.bestmovies.model.tv.detail.TvShowDetail;
import org.tek.geza.bestmovies.presenter.usecase.load.GetTvShowDetail;

import rx.Observable;

/**
 * Created by gezacsorba on 12/12/2016.
 */

public class TvShowDetailsPresenter {

    GetTvShowDetail getTvShowDetail;

    public TvShowDetailsPresenter(GetTvShowDetail getTvShowDetail) {
        this.getTvShowDetail = getTvShowDetail;
    }

    public Observable<TvShowDetail> getDetails(int id) {
        return getTvShowDetail.execute(id);
    }
}
