package org.tek.geza.bestmovies.presenter.usecase.load;

import org.tek.geza.bestmovies.model.movie.detail.MovieDetails;
import org.tek.geza.bestmovies.model.repository.MovieRepository;
import org.tek.geza.bestmovies.presenter.usecase.UseCase;
import org.tek.geza.bestmovies.util.transformer.MovieAndPosterMerger;

import rx.Observable;

public class GetMovieDetails implements UseCase<Observable<MovieDetails>, Integer> {

    MovieRepository repository;
    MovieAndPosterMerger merger;

    public GetMovieDetails(MovieRepository repository, MovieAndPosterMerger merger) {
        this.repository = repository;
        this.merger = merger;
    }

    @Override
    public Observable<MovieDetails> execute(Integer id) {
        return repository.getMovieDetails(id)
                .zipWith(repository.getMovieImages(id), merger);
    }
}
