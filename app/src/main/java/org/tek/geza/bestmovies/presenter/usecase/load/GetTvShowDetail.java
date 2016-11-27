package org.tek.geza.bestmovies.presenter.usecase.load;

import org.tek.geza.bestmovies.model.repository.TvRepository;
import org.tek.geza.bestmovies.model.tv.detail.TvShowDetail;
import org.tek.geza.bestmovies.presenter.usecase.UseCase;

import rx.Observable;

public class GetTvShowDetail implements UseCase<Observable<TvShowDetail>, Integer> {

    TvRepository tvRepository;

    public GetTvShowDetail(TvRepository tvRepository) {
        this.tvRepository = tvRepository;
    }

    @Override
    public Observable<TvShowDetail> execute(Integer id) {
        return tvRepository.getTvShowDetail(id);
    }
}
