package org.tek.geza.bestmovies.presenter.usecase.load;

import org.tek.geza.bestmovies.model.repository.TvRepository;
import org.tek.geza.bestmovies.model.tv.list.TvShow;
import org.tek.geza.bestmovies.presenter.usecase.UseCase;

import rx.Observable;

public class GetTvShows implements UseCase<Observable<TvShow>, Void> {

    private final static int MOVIE_COUNT = 20;
    TvRepository tvRepository;

    public GetTvShows(TvRepository tvRepository) {
        this.tvRepository = tvRepository;
    }

    @Override
    public Observable<TvShow> execute(Void param) {
        return tvRepository.get20TopTvShows().take(MOVIE_COUNT);
    }
}
