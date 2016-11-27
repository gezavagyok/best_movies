package org.tek.geza.bestmovies.presenter.usecase.load;

import org.tek.geza.bestmovies.model.movie.list.Movie;
import org.tek.geza.bestmovies.model.repository.MovieRepository;
import org.tek.geza.bestmovies.presenter.usecase.UseCase;

import rx.Observable;

public class GetTop20Movies implements UseCase<Observable<Movie>, Void> {

    private final static int MOVIE_COUNT = 20;
    MovieRepository repository;

    public GetTop20Movies(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<Movie> execute(Void param) {
        return repository.getTopMovies().take(MOVIE_COUNT);
    }
}
