package org.tek.geza.bestmovies;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.tek.geza.bestmovies.model.movie.list.Movie;
import org.tek.geza.bestmovies.model.movie.response.MovieResponse;
import org.tek.geza.bestmovies.model.people.Person;
import org.tek.geza.bestmovies.model.people.response.PeopleResponse;
import org.tek.geza.bestmovies.model.tv.list.TvShow;
import org.tek.geza.bestmovies.model.tv.response.TvShowResponse;
import org.tek.geza.bestmovies.util.transformer.MovieTransformer;
import org.tek.geza.bestmovies.util.transformer.StarTransformer;
import org.tek.geza.bestmovies.util.transformer.TvShowTransformer;

import rx.Observable;

import static org.mockito.BDDMockito.given;


@Config(constants = BuildConfig.class, sdk = 21,
        manifest = "app/src/main/AndroidManifest.xml")
@RunWith(RobolectricGradleTestRunner.class)
public class TransformerTest {

    @Mock
    StarTransformer starTransformer;

    @Mock
    MovieTransformer movieTransformer;

    @Mock
    TvShowTransformer tvShowTransformer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTransform() throws Exception {
        given(starTransformer.call(new PeopleResponse())).willReturn(Observable.just(new Person()));
        given(movieTransformer.call(new MovieResponse())).willReturn(Observable.just(new Movie()));
        given(tvShowTransformer.call(new TvShowResponse())).willReturn(Observable.just(new TvShow()));
    }
}