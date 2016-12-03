package org.tek.geza.bestmovies.util.transformer;

import org.tek.geza.bestmovies.model.movie.detail.MovieDetails;
import org.tek.geza.bestmovies.model.movie.response.review.ReviewResponse;

import rx.functions.Func2;

/**
 * Created by gezacsorba on 01/12/2016.
 */

public class MovieAndReviewMerger implements Func2<MovieDetails,ReviewResponse,MovieDetails> {

    @Override
    public MovieDetails call(MovieDetails movieDetails, ReviewResponse reviewResponse) {
        movieDetails.setReviews(reviewResponse.getReviews());
        return movieDetails;
    }
}
