package org.tek.geza.bestmovies.presenter;

import android.app.Activity;

import com.google.android.gms.ads.AdRequest;

import org.tek.geza.bestmovies.presenter.usecase.PurchaseUseCase;

import rx.Observable;


public class HomePresenter {

    PurchaseUseCase useCase;

    public HomePresenter(PurchaseUseCase useCase) {
        this.useCase = useCase;
    }

    public Observable<Boolean> startPurchaseProcess(Activity activity) {
        return useCase.execute(activity);
    }

    public AdRequest getAdSetup() {
        return new AdRequest.Builder()
                .addKeyword("movie")
                .addKeyword("tv show")
                .addKeyword("actor")
                .addKeyword("movie review")
                .addKeyword("boring")
                .addKeyword("program")
                .build();
    }
}
