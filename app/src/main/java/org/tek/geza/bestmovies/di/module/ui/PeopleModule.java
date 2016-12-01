package org.tek.geza.bestmovies.di.module.ui;

import org.tek.geza.bestmovies.model.MovieDbApi;
import org.tek.geza.bestmovies.model.repository.StarRepository;
import org.tek.geza.bestmovies.presenter.PeoplePresenter;
import org.tek.geza.bestmovies.presenter.usecase.load.GetFamousPeople;
import org.tek.geza.bestmovies.presenter.usecase.search.SearchStars;
import org.tek.geza.bestmovies.util.transformer.StarTransformer;

import dagger.Module;
import dagger.Provides;

@Module
public class PeopleModule {

    @Provides
    StarRepository provideStarRepositor(MovieDbApi api, StarTransformer transformer) {
        return new StarRepository(api, transformer);
    }

    @Provides
    GetFamousPeople provideGetFamousPeople(StarRepository starRepository) {
        return new GetFamousPeople(starRepository);
    }

    @Provides
    SearchStars provideSearchStars(StarRepository starRepository) {
        return new SearchStars(starRepository);
    }

    @Provides
    PeoplePresenter providePeoplePresenter(GetFamousPeople getFamousPeople,
                                           SearchStars searchStars) {
        return new PeoplePresenter(getFamousPeople, searchStars);
    }

}
