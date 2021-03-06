package org.tek.geza.bestmovies.presenter.usecase.search;

import org.tek.geza.bestmovies.model.people.Person;
import org.tek.geza.bestmovies.model.repository.StarRepository;
import org.tek.geza.bestmovies.presenter.usecase.UseCase;

import rx.Observable;

public class SearchStars implements UseCase<Observable<Person>, String> {

    StarRepository starRepository;

    public SearchStars(StarRepository starRepository) {
        this.starRepository = starRepository;
    }

    @Override
    public Observable<Person> execute(String param) {
        return starRepository.searchStars(param);
    }
}
