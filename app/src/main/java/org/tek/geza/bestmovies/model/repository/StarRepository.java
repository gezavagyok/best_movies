package org.tek.geza.bestmovies.model.repository;

import org.tek.geza.bestmovies.model.MovieDbApi;
import org.tek.geza.bestmovies.model.people.Person;
import org.tek.geza.bestmovies.util.transformer.StarTransformer;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by geza on 2016.07.24..
 */

public class StarRepository {

    MovieDbApi api;
    StarTransformer starTransformer;

    public StarRepository(MovieDbApi api, StarTransformer starTransformer) {
        this.starTransformer = starTransformer;
        this.api = api;
    }

    public Observable<Person> getPopularPeople() {
        return api.getPopularPeople()
                .flatMap(starTransformer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Person> searchStars(String query) {
        return api.searchPeople(query)
                .flatMap(starTransformer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
