package org.tek.geza.bestmovies.presenter.usecase.search;

import org.tek.geza.bestmovies.model.movie.list.Movie;
import org.tek.geza.bestmovies.model.repository.MovieRepository;
import org.tek.geza.bestmovies.presenter.usecase.UseCase;

import rx.Observable;

public class SearchForMovie implements UseCase<Observable<Movie>,String> {

    MovieRepository movieRepository;

    public SearchForMovie(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Observable<Movie> execute(String query) {
        return movieRepository.searchTopMovies(query);
    }
}
