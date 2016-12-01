package org.tek.geza.bestmovies.util.transformer;


import org.tek.geza.bestmovies.model.people.Person;
import org.tek.geza.bestmovies.model.people.response.KnownFor;
import org.tek.geza.bestmovies.model.people.response.Result;
import org.tek.geza.bestmovies.model.people.response.PeopleResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class StarTransformer implements Func1<PeopleResponse, Observable<Person>> {

    @Inject
    public StarTransformer() {

    }

    @Override
    public Observable<Person> call(PeopleResponse peopleResponse) {
        List<Result> results = peopleResponse.getResults();
        List<Person> persons = new ArrayList<>(30);
        for (Result r : results) {
            Person person = new Person();
            person.setId(r.getId());
            person.setName(r.getName());
            StringBuilder sb = new StringBuilder();
            for(KnownFor kf : r.getKnownFor()){
                sb.append(kf.getTitle()).append("\n");
            }
            person.setMovies(sb.toString());
            person.setProfilePath(r.getProfilePath());
            persons.add(person);
        }
        return Observable.from(persons);
    }
}
