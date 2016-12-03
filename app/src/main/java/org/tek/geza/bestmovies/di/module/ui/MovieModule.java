package org.tek.geza.bestmovies.di.module.ui;

import org.tek.geza.bestmovies.model.MovieDbApi;
import org.tek.geza.bestmovies.model.repository.MovieRepository;
import org.tek.geza.bestmovies.presenter.MoviePresenter;
import org.tek.geza.bestmovies.presenter.usecase.load.GetMovieDetails;
import org.tek.geza.bestmovies.presenter.usecase.load.GetTop20Movies;
import org.tek.geza.bestmovies.presenter.usecase.search.SearchForMovie;
import org.tek.geza.bestmovies.util.transformer.MovieAndPosterMerger;
import org.tek.geza.bestmovies.util.transformer.MovieAndReviewMerger;
import org.tek.geza.bestmovies.util.transformer.MovieTransformer;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieModule {

    @Provides
    MovieAndPosterMerger provideMovieAndPosterMerger() {
        return new MovieAndPosterMerger();
    }

    @Provides
    MovieAndReviewMerger provideReviewMerger() {
        return new MovieAndReviewMerger();
    }

    @Provides
    MovieRepository provideMovieRepository(MovieDbApi api, MovieTransformer transformer) {
        return new MovieRepository(api, transformer);
    }

    @Provides
    GetTop20Movies provideSearchForMovies(MovieRepository repository) {
        return new GetTop20Movies(repository);
    }

    @Provides
    GetMovieDetails provideGetMovieDetails(MovieRepository repository, MovieAndPosterMerger merger, MovieAndReviewMerger reviewMerger) {
        return new GetMovieDetails(repository, merger, reviewMerger);
    }

    @Provides
    SearchForMovie provideSearchForMovie(MovieRepository repository) {
        return new SearchForMovie(repository);
    }

    @Provides
    MoviePresenter provideMoviePresenter(GetTop20Movies getTop20Movies,
                                         GetMovieDetails getMovieDetails,
                                         SearchForMovie searchForMovie) {
        return new MoviePresenter(getTop20Movies, getMovieDetails, searchForMovie);
    }
}
