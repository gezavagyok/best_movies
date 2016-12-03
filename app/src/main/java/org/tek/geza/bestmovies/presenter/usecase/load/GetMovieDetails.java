package org.tek.geza.bestmovies.presenter.usecase.load;

import org.tek.geza.bestmovies.model.movie.detail.MovieDetails;
import org.tek.geza.bestmovies.model.repository.MovieRepository;
import org.tek.geza.bestmovies.presenter.usecase.UseCase;
import org.tek.geza.bestmovies.util.transformer.MovieAndPosterMerger;
import org.tek.geza.bestmovies.util.transformer.MovieAndReviewMerger;

import rx.Observable;

public class GetMovieDetails implements UseCase<Observable<MovieDetails>, Integer> {

    MovieRepository repository;
    MovieAndPosterMerger posterMerger;
    MovieAndReviewMerger reviewMerger;

    public GetMovieDetails(MovieRepository repository, MovieAndPosterMerger posterMerger, MovieAndReviewMerger reviewMerger) {
        this.repository = repository;
        this.posterMerger = posterMerger;
        this.reviewMerger = reviewMerger;
    }

    @Override
    public Observable<MovieDetails> execute(Integer id) {
        return repository.getMovieDetails(id)
                .zipWith(repository.getMovieImages(id), posterMerger)
                .zipWith(repository.getReviews(id), reviewMerger);
    }
}
