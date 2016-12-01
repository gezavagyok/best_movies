package org.tek.geza.bestmovies.presenter;

import org.tek.geza.bestmovies.model.people.Person;
import org.tek.geza.bestmovies.presenter.usecase.load.GetFamousPeople;
import org.tek.geza.bestmovies.presenter.usecase.search.SearchStars;

import rx.Observable;

public class PeoplePresenter {

    GetFamousPeople getFamousPeople;
    SearchStars searchStars;

    public PeoplePresenter(GetFamousPeople getFamousPeople, SearchStars searchStars) {
        this.getFamousPeople = getFamousPeople;
        this.searchStars = searchStars;
    }

    public Observable<Person> refreshList() {
        return getFamousPeople.execute(null);
    }

    public Observable<Person> searchStars(String query) {
        return searchStars.execute(query);
    }
}
