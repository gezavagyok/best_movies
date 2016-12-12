package org.tek.geza.bestmovies;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.tek.geza.bestmovies.model.people.Person;
import org.tek.geza.bestmovies.model.repository.StarRepository;

import rx.Observable;

import static org.mockito.Mockito.when;

@Config(constants = BuildConfig.class, sdk = 21,
        manifest = "app/src/main/AndroidManifest.xml")
@RunWith(RobolectricGradleTestRunner.class)
public class RepositoryUnitTest {

    @Mock
    StarRepository repository;

    Person fakePersonGeza;

    Person fakePersonVirag;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        fakePersonGeza = new Person();
        fakePersonGeza.setName("Geza");
        fakePersonGeza.setProfilePath("facebook.com/gcsorba");
        fakePersonGeza.setId(42);
        fakePersonGeza.setMovies("fifty repperek");

        fakePersonVirag = new Person();
        fakePersonVirag.setName("Virág");
        fakePersonVirag.setProfilePath("facebook.com/vbuka");
        fakePersonVirag.setId(43);
        fakePersonVirag.setMovies("Love Actually");
    }

    @Test
    public void test_repositoryReturnSize() throws Exception {
        when(repository.getPopularPeople()).thenReturn(Observable.just(fakePersonGeza,fakePersonVirag));
        RxAssertions.subscribeAssertingThat(repository.getPopularPeople())
                .hasSize(2)
                .emits(fakePersonGeza,fakePersonVirag)
                .completesSuccessfully();

        when(repository.getPopularPeople()).thenReturn(Observable.just(fakePersonGeza));
        RxAssertions.subscribeAssertingThat(repository.getPopularPeople())
                .hasSize(1)
                .completesSuccessfully();
    }
}