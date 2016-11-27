package org.tek.geza.bestmovies.presenter.usecase.load;

import org.tek.geza.bestmovies.model.movie.detail.MovieDetails;
import org.tek.geza.bestmovies.model.repository.MovieRepository;
import org.tek.geza.bestmovies.presenter.usecase.UseCase;

import rx.Observable;

public class GetMovieDetails implements UseCase<Observable<MovieDetails>, Integer> {

    MovieRepository repository;

    public GetMovieDetails(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<MovieDetails> execute(Integer id) {
        return repository.getMovieDetails(id);
    }
}
