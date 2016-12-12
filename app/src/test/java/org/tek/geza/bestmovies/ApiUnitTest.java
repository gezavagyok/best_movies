package org.tek.geza.bestmovies;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.tek.geza.bestmovies.model.MovieDbApi;
import org.tek.geza.bestmovies.model.movie.detail.MovieDetails;
import org.tek.geza.bestmovies.model.movie.response.MovieResponse;
import org.tek.geza.bestmovies.model.movie.response.image.ImageResponse;
import org.tek.geza.bestmovies.model.people.response.PeopleResponse;
import org.tek.geza.bestmovies.model.tv.detail.TvShowDetail;
import org.tek.geza.bestmovies.model.tv.response.TvShowResponse;

import rx.Observable;

import static org.mockito.BDDMockito.given;

@Config(constants = BuildConfig.class, sdk = 21,
        manifest = "app/src/main/AndroidManifest.xml")
@RunWith(RobolectricGradleTestRunner.class)
public class ApiUnitTest {

    public static final int FAKE_ID = 0;
    public static final String FAKE_STRING = "asd";

    @Mock
    MovieDbApi api;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void apiTestGetLists() throws Exception {
        given(api.getPopularPeople()).willReturn(Observable.just(new PeopleResponse()));
        given(api.getPopularTvShows()).willReturn(Observable.just(new TvShowResponse()));
        given(api.getPopularMovies()).willReturn(Observable.just(new MovieResponse()));
    }

    @Test
    public void apiTestGetDetails() throws Exception {
        given(api.getMovieDetail(FAKE_ID)).willReturn(Observable.just(new MovieDetails()));
        given(api.getTvShowDetail(FAKE_ID)).willReturn(Observable.just(new TvShowDetail()));
    }

    @Test
    public void apiTestGetPosters() throws Exception {
        given(api.getMovieImages(FAKE_ID)).willReturn(Observable.just(new ImageResponse()));
    }

    @Test
    public void apiTestSearch() throws Exception {
        given(api.searchMovies(FAKE_STRING)).willReturn(Observable.just(new MovieResponse()));
        given(api.searchPeople(FAKE_STRING)).willReturn(Observable.just(new PeopleResponse()));
        given(api.searchTvShows(FAKE_STRING)).willReturn(Observable.just(new TvShowResponse()));
    }
}