package org.tek.geza.bestmovies.view.fragment;

import android.support.v7.widget.LinearLayoutManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.tek.geza.bestmovies.di.component.DaggerTvShowComponent;
import org.tek.geza.bestmovies.di.component.TvShowComponent;
import org.tek.geza.bestmovies.model.tv.list.TvShow;
import org.tek.geza.bestmovies.presenter.TvShowPresenter;
import org.tek.geza.bestmovies.util.event.LoadRequestEvent;
import org.tek.geza.bestmovies.util.event.SearchRequestEvent;
import org.tek.geza.bestmovies.util.network.ErrorHandler;
import org.tek.geza.bestmovies.view.activity.MainActivity;
import org.tek.geza.bestmovies.view.adapter.tv.TvAdapter;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

public class TvShowFragment extends ContentListFragment {

    @Inject
    TvAdapter tvAdapter;

    @Inject
    TvShowPresenter presenter;

    @Inject
    LinearLayoutManager layoutManager;

    @Inject
    ErrorHandler errorHandler;

    @Override
    protected void setupRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(tvAdapter);
    }

    @Subscribe
    public void onLoadRequest(LoadRequestEvent event) {
        if (getUserVisibleHint()) {
            tvAdapter.clear();
            onRefresh();
        }
    }

    @Subscribe
    public void onSearchRequest(SearchRequestEvent event) {
        if (!getUserVisibleHint()) return;

        tvAdapter.clear();
        Subscription s = presenter.searchTvShow(event.getQuery().toString())
                .doOnNext(new Action1<TvShow>() {
                    @Override
                    public void call(TvShow show) {
                        tvAdapter.addTvShow(show);
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        tvAdapter.notifyDataSetChanged();
                    }
                })
                .onErrorReturn(new Func1<Throwable, TvShow>() {
                    @Override
                    public TvShow call(Throwable throwable) {
                        errorHandler.onError(throwable);
                        return TvShow.error();
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
                .doOnNext(new Action1<TvShow>() {
                    @Override
                    public void call(TvShow tvShow) {
                        tvAdapter.addTvShow(tvShow);
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        tvAdapter.notifyDataSetChanged();
                    }
                })
                .onErrorReturn(new Func1<Throwable, TvShow>() {
                    @Override
                    public TvShow call(Throwable throwable) {
                        errorHandler.onError(throwable);
                        return TvShow.error();
                    }
                }).subscribe();
        subscription.add(s);
    }

    @Override
    protected void inject() {
        this.component = DaggerTvShowComponent.builder()
                .activityModule(((MainActivity) getActivity()).getComponent())
                .build();
        ((TvShowComponent) component).inject(this);
    }
}
