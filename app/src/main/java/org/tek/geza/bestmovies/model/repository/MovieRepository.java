package org.tek.geza.bestmovies.model.repository;

import org.tek.geza.bestmovies.model.MovieDbApi;
import org.tek.geza.bestmovies.model.movie.detail.MovieDetails;
import org.tek.geza.bestmovies.model.movie.list.Movie;
import org.tek.geza.bestmovies.model.movie.response.image.ImageResponse;
import org.tek.geza.bestmovies.model.movie.response.review.ReviewResponse;
import org.tek.geza.bestmovies.util.transformer.MovieTransformer;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MovieRepository {

    MovieDbApi api;
    MovieTransformer transformer;

    public MovieRepository(MovieDbApi api, MovieTransformer transformer) {
        this.api = api;
        this.transformer = transformer;
    }

    public Observable<Movie> getTopMovies() {
        return api.getPopularMovies()
                .flatMap(transformer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Movie> searchTopMovies(String query) {
        return api.searchMovies(query)
                .flatMap(transformer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<MovieDetails> getMovieDetails(int id) {
        return api.getMovieDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ImageResponse> getMovieImages(int id) {
        return api.getMovieImages(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<ReviewResponse> getReviews(int id) {
        return api.getReviews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
