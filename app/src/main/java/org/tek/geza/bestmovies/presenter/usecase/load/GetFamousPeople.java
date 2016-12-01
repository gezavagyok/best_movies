package org.tek.geza.bestmovies.presenter.usecase.load;

import org.tek.geza.bestmovies.model.people.Person;
import org.tek.geza.bestmovies.model.repository.StarRepository;
import org.tek.geza.bestmovies.presenter.usecase.UseCase;

import rx.Observable;

public class GetFamousPeople implements UseCase<Observable<Person>, Void> {

    StarRepository starRepository;

    public GetFamousPeople(StarRepository starRepository) {
        this.starRepository = starRepository;
    }

    @Override
    public Observable<Person> execute(Void param) {
        return starRepository.getPopularPeople();
    }
}
