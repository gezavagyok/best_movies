package org.tek.geza.bestmovies.util.transformer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.tek.geza.bestmovies.BuildConfig;
import org.tek.geza.bestmovies.model.people.Person;
import org.tek.geza.bestmovies.model.people.response.PeopleResponse;

import rx.Observable;

import static org.mockito.BDDMockito.given;

/**
 * Created by gezacsorba on 28/11/2016.
 */
@Config(constants = BuildConfig.class, sdk = 21,
        manifest = "app/src/main/AndroidManifest.xml")
@RunWith(RobolectricGradleTestRunner.class)
public class StarTransformerTest {

    @Mock
    StarTransformer transformer;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTransform() throws Exception {
        given(transformer.call(new PeopleResponse())).willReturn(Observable.just(new Person()));
    }
}