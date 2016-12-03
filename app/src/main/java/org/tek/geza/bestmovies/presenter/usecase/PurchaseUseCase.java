package org.tek.geza.bestmovies.presenter.usecase;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import org.tek.geza.bestmovies.billing.IabHelper;
import org.tek.geza.bestmovies.billing.IabResult;
import org.tek.geza.bestmovies.billing.Purchase;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class PurchaseUseCase implements UseCase<Observable<Boolean>, Activity> {
    @Override
    public Observable<Boolean> execute(final Activity activity) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(final Subscriber<? super Boolean> subscriber) {
                IabHelper helper = new IabHelper(activity, "key");
                try {
                    helper.launchPurchaseFlow(activity, "sku", 0, new IabHelper.OnIabPurchaseFinishedListener() {
                        @Override
                        public void onIabPurchaseFinished(IabResult result, Purchase info) {
                            // this isn't the most secure way to store the data of purchases, however
                            // the topic is too large to go deeper.
                            SharedPreferences preferences = activity.getSharedPreferences("default", Context.MODE_PRIVATE);
                            preferences.edit().putBoolean("adfree", result.isSuccess());
                            subscriber.onNext(result.isSuccess());
                        }
                    });
                } catch (IabHelper.IabAsyncInProgressException e) {
                    subscriber.onError(e);
                }
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
