package org.tek.geza.bestmovies.di.module.ui;

import android.support.v4.app.FragmentManager;

import org.tek.geza.bestmovies.di.module.ActivityModule;
import org.tek.geza.bestmovies.presenter.HomePresenter;
import org.tek.geza.bestmovies.presenter.usecase.PurchaseUseCase;
import org.tek.geza.bestmovies.view.adapter.MovieDbPager;

import dagger.Module;
import dagger.Provides;

@Module(includes = ActivityModule.class)
public class HomeModule {

    @Provides
    MovieDbPager provideMovieDbPager(FragmentManager manager) {
        return new MovieDbPager(manager);
    }

    @Provides
    PurchaseUseCase providePurchaseUseCase() {
        return new PurchaseUseCase();
    }

    @Provides
    HomePresenter provideHomePresenter(PurchaseUseCase purchaseUseCase) {
        return new HomePresenter(purchaseUseCase);
    }
}
