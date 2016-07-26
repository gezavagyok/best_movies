package org.tek.geza.bestmovies.view.fragment;

import android.support.v7.widget.GridLayoutManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.tek.geza.bestmovies.di.component.DaggerPersonComponent;
import org.tek.geza.bestmovies.di.component.PersonComponent;
import org.tek.geza.bestmovies.model.people.Star;
import org.tek.geza.bestmovies.presenter.PeoplePresenter;
import org.tek.geza.bestmovies.util.event.LoadRequestEvent;
import org.tek.geza.bestmovies.util.event.SearchRequestEvent;
import org.tek.geza.bestmovies.util.network.ErrorHandler;
import org.tek.geza.bestmovies.view.activity.MainActivity;
import org.tek.geza.bestmovies.view.adapter.star.StarAdapter;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

public class StarFragment extends ContentListFragment {

    @Inject
    StarAdapter starAdapter;

    @Inject
    GridLayoutManager gridLayoutManager;

    @Inject
    PeoplePresenter presenter;

    @Inject
    ErrorHandler errorHandler;

    @Override
    protected void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(starAdapter);
    }

    @Subscribe
    public void onLoadRequest(LoadRequestEvent event) {
        if (getUserVisibleHint()) {
            starAdapter.clear();
            onRefresh();
        }
    }

    @Subscribe
    public void onSearchRequest(SearchRequestEvent event) {
        if (!getUserVisibleHint()) return;

        starAdapter.clear();
        Subscription s = presenter.searchStars(event.getQuery().toString())
                .doOnNext(new Action1<Star>() {
                    @Override
                    public void call(Star star) {
                        starAdapter.addStar(star);
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        starAdapter.notifyDataSetChanged();
                    }
                })
                .onErrorReturn(new Func1<Throwable, Star>() {
                    @Override
                    public Star call(Throwable throwable) {
                        errorHandler.onError(throwable);
                        return Star.error();
                    }
                })
                .subscribe();
        subscription.add(s);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        onRefresh();
    }

    @Override
    public void onStop() {
        super.onStop();
        subscription.unsubscribe();
    }


    public void onRefresh() {
        Subscription s = presenter.refreshList()
                .doOnNext(new Action1<Star>() {
                    @Override
                    public void call(Star star) {
                        starAdapter.addStar(star);
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        starAdapter.notifyDataSetChanged();
                    }
                })
                .onErrorReturn(new Func1<Throwable, Star>() {
                    @Override
                    public Star call(Throwable throwable) {
                        errorHandler.onError(throwable);
                        return Star.error();
                    }
                }).subscribe();
        subscription.add(s);
    }

    @Override
    protected void inject() {
        this.component = DaggerPersonComponent.builder()
                .activityModule(((MainActivity) getActivity()).getComponent())
                .build();
        ((PersonComponent) component).inject(this);
    }
}
