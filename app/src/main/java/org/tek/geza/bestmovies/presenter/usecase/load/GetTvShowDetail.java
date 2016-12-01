package org.tek.geza.bestmovies.presenter.usecase.load;

import org.tek.geza.bestmovies.model.repository.TvRepository;
import org.tek.geza.bestmovies.model.tv.detail.TvShowDetail;
import org.tek.geza.bestmovies.presenter.usecase.UseCase;
import org.tek.geza.bestmovies.util.transformer.TvShowAndPosterMerger;

import rx.Observable;

public class GetTvShowDetail implements UseCase<Observable<TvShowDetail>, Integer> {

    TvRepository tvRepository;
    TvShowAndPosterMerger merger;

    public GetTvShowDetail(TvRepository tvRepository, TvShowAndPosterMerger merger) {
        this.tvRepository = tvRepository;
        this.merger = merger;
    }

    @Override
    public Observable<TvShowDetail> execute(Integer id) {
        return tvRepository.getTvShowDetail(id)
                .zipWith(tvRepository.getTvShowImages(id), merger);
    }
}
