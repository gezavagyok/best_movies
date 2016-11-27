package org.tek.geza.bestmovies.presenter.usecase.load;

import org.tek.geza.bestmovies.model.people.Star;
import org.tek.geza.bestmovies.model.repository.StarRepository;
import org.tek.geza.bestmovies.presenter.usecase.UseCase;

import rx.Observable;

public class GetFamousPeople implements UseCase<Observable<Star>, Void> {

    StarRepository starRepository;

    public GetFamousPeople(StarRepository starRepository) {
        this.starRepository = starRepository;
    }

    @Override
    public Observable<Star> execute(Void param) {
        return starRepository.getPopularStars();
    }
}
