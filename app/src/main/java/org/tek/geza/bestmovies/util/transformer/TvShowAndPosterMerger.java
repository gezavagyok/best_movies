package org.tek.geza.bestmovies.util.transformer;

import org.tek.geza.bestmovies.model.movie.response.image.ImageResponse;
import org.tek.geza.bestmovies.model.tv.detail.TvShowDetail;

import javax.inject.Inject;

import rx.functions.Func2;

/**
 * Created by gezacsorba on 01/12/2016.
 */

public class TvShowAndPosterMerger implements Func2<TvShowDetail,ImageResponse, TvShowDetail> {

    @Inject
    public TvShowAndPosterMerger() {
    }

    @Override
    public TvShowDetail call(TvShowDetail tvShowDetail, ImageResponse imageResponse) {
        tvShowDetail.setPosters(imageResponse.getPosters());
        return tvShowDetail;
    }
}
