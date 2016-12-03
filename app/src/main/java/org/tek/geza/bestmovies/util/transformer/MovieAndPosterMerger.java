package org.tek.geza.bestmovies.util.transformer;

import org.tek.geza.bestmovies.model.movie.detail.MovieDetails;
import org.tek.geza.bestmovies.model.movie.response.image.ImageResponse;

import rx.functions.Func2;

/**
 * Created by gezacsorba on 01/12/2016.
 */

public class MovieAndPosterMerger implements Func2<MovieDetails,ImageResponse,MovieDetails> {

    @Override
    public MovieDetails call(MovieDetails movieDetails, ImageResponse imageResponse) {
        movieDetails.setImageUrls(imageResponse.getPosters());
        return movieDetails;
    }
}
