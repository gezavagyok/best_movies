package org.tek.geza.bestmovies.presenter;

import org.tek.geza.bestmovies.model.movie.list.Movie;
import org.tek.geza.bestmovies.presenter.usecase.load.GetTop20Movies;
import org.tek.geza.bestmovies.presenter.usecase.search.SearchForMovie;

import rx.Observable;

public class MoviePresenter {

    GetTop20Movies getTop20Movies;
    SearchForMovie searchForMovie;

    public MoviePresenter(GetTop20Movies getTop20Movies, SearchForMovie searchForMovie) {
        this.getTop20Movies = getTop20Movies;
        this.searchForMovie = searchForMovie;
    }

    public Observable<Movie> refreshList() {
        return getTop20Movies.execute(null);
    }

    public Observable<Movie> searchMovie(String s) {
        return searchForMovie.execute(s);
    }
}
