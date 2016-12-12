package org.tek.geza.bestmovies.presenter;

import org.tek.geza.bestmovies.model.movie.detail.MovieDetails;
import org.tek.geza.bestmovies.presenter.usecase.load.GetMovieDetails;

import rx.Observable;

/**
 * Created by gezacsorba on 12/12/2016.
 */

public class MovieDetailPresenter {

    GetMovieDetails getMovieDetails;

    public MovieDetailPresenter(GetMovieDetails getMovieDetails) {
        this.getMovieDetails = getMovieDetails;
    }

    public Observable<MovieDetails> getMovieDetail(int id) {
        return getMovieDetails.execute(id);
    }
}
