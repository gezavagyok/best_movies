package org.tek.geza.bestmovies.di.module.ui;

import org.tek.geza.bestmovies.model.MovieDbApi;
import org.tek.geza.bestmovies.model.repository.TvRepository;
import org.tek.geza.bestmovies.presenter.TvShowDetailsPresenter;
import org.tek.geza.bestmovies.presenter.TvShowPresenter;
import org.tek.geza.bestmovies.presenter.usecase.load.GetTvShowDetail;
import org.tek.geza.bestmovies.presenter.usecase.load.GetTvShows;
import org.tek.geza.bestmovies.presenter.usecase.search.SearchForTvShow;
import org.tek.geza.bestmovies.util.transformer.TvShowAndPosterMerger;
import org.tek.geza.bestmovies.util.transformer.TvShowTransformer;

import dagger.Module;
import dagger.Provides;

@Module
public class TvShowModule {

    @Provides
    TvShowAndPosterMerger provideMerger() {
        return new TvShowAndPosterMerger();
    }

    @Provides
    TvRepository provideTvRepository(MovieDbApi api, TvShowTransformer transformer) {
        return new TvRepository(api, transformer);
    }

    @Provides
    GetTvShows provideGetTvShows(TvRepository repository) {
        return new GetTvShows(repository);
    }

    @Provides
    GetTvShowDetail provideGetTvShowDetail(TvRepository repository, TvShowAndPosterMerger merger) {
        return new GetTvShowDetail(repository, merger);
    }

    @Provides
    SearchForTvShow provideSearchForTvShow(TvRepository repository) {
        return new SearchForTvShow(repository);
    }

    @Provides
    TvShowPresenter provideTvShowPresenter(GetTvShows getTvShows,
                                           SearchForTvShow searchForTvShow) {
        return new TvShowPresenter(getTvShows, searchForTvShow);
    }

    @Provides
    TvShowDetailsPresenter provideTvShowDetailsPresenter(GetTvShowDetail getTvShowDetail) {
        return new TvShowDetailsPresenter(getTvShowDetail);
    }
}
