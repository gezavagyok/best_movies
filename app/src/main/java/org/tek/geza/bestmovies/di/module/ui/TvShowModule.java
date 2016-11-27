package org.tek.geza.bestmovies.di.module.ui;

import org.tek.geza.bestmovies.model.MovieDbApi;
import org.tek.geza.bestmovies.model.repository.TvRepository;
import org.tek.geza.bestmovies.presenter.TvShowPresenter;
import org.tek.geza.bestmovies.presenter.usecase.load.GetTvShowDetail;
import org.tek.geza.bestmovies.presenter.usecase.load.GetTvShows;
import org.tek.geza.bestmovies.presenter.usecase.search.SearchForTvShow;
import org.tek.geza.bestmovies.util.transformer.TvShowTransformer;

import dagger.Module;
import dagger.Provides;

@Module
public class TvShowModule {

    @Provides
    TvRepository provideTvRepository(MovieDbApi api, TvShowTransformer transformer) {
        return new TvRepository(api, transformer);
    }

    @Provides
    GetTvShows provideGetTvShows(TvRepository repository) {
        return new GetTvShows(repository);
    }

    @Provides
    GetTvShowDetail provideGetTvShowDetail(TvRepository repository) {
        return new GetTvShowDetail(repository);
    }

    @Provides
    SearchForTvShow provideSearchForTvShow(TvRepository repository) {
        return new SearchForTvShow(repository);
    }

    @Provides
    TvShowPresenter provideTvShowPresenter(GetTvShows getTvShows,
                                           GetTvShowDetail getTvShowDetail,
                                           SearchForTvShow searchForTvShow) {
        return new TvShowPresenter(getTvShows, getTvShowDetail, searchForTvShow);
    }
}
