package org.tek.geza.bestmovies.model;

import org.tek.geza.bestmovies.model.movie.detail.MovieDetails;
import org.tek.geza.bestmovies.model.movie.response.MovieResponse;
import org.tek.geza.bestmovies.model.movie.response.image.ImageResponse;
import org.tek.geza.bestmovies.model.movie.response.review.ReviewResponse;
import org.tek.geza.bestmovies.model.people.response.PeopleResponse;
import org.tek.geza.bestmovies.model.tv.detail.TvShowDetail;
import org.tek.geza.bestmovies.model.tv.response.TvShowResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface MovieDbApi {

    // STAR
    @GET("person/popular")
    Observable<PeopleResponse> getPopularPeople();

    // MOVIE
    @GET("movie/top_rated")
    Observable<MovieResponse> getPopularMovies();

    @GET("movie/{id}")
    Observable<MovieDetails> getMovieDetail(@Path("id") int id);

    @GET("movie/{movie_id}/images")
    Observable<ImageResponse> getMovieImages(@Path("movie_id") int id);

    @GET("movie/{movie_id}/reviews")
    Observable<ReviewResponse> getReviews(@Path("movie_id") int id);

    // TV SHOW
    @GET("tv/popular")
    Observable<TvShowResponse> getPopularTvShows();

    @GET("tv/{id}")
    Observable<TvShowDetail> getTvShowDetail(@Path("id") int id);

    @GET("tv/{tv_id}/images")
    Observable<ImageResponse> getTvShowImages(@Path("tv_id") int id);

    // SEARCH
    @GET("search/movie")
    Observable<MovieResponse> searchMovies(@Query("query") String query);

    @GET("search/tv")
    Observable<TvShowResponse> searchTvShows(@Query("query") String query);

    @GET("search/person")
    Observable<PeopleResponse> searchPeople(@Query("query") String query);

}
