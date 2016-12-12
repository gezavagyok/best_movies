package org.tek.geza.bestmovies.view.fragment;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.tek.geza.bestmovies.BestMoviesApplication;
import org.tek.geza.bestmovies.di.module.ui.TvShowModule;
import org.tek.geza.bestmovies.model.tv.list.TvShow;
import org.tek.geza.bestmovies.presenter.TvShowPresenter;
import org.tek.geza.bestmovies.util.event.LoadRequestEvent;
import org.tek.geza.bestmovies.util.event.SearchRequestEvent;
import org.tek.geza.bestmovies.util.network.ErrorHandler;
import org.tek.geza.bestmovies.view.activity.HomeActivity;
import org.tek.geza.bestmovies.view.adapter.tv.TvAdapter;

import java.util.Map;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

public class TvShowFragment extends ContentListFragment {

    TvAdapter tvAdapter;
    LinearLayoutManager layoutManager;

    @Inject
    TvShowPresenter presenter;

    @Inject
    ErrorHandler errorHandler;

    void onInit(){
        layoutManager = new LinearLayoutManager(getActivity());
        tvAdapter = new TvAdapter((AppCompatActivity) getActivity());
    }

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

        Map hits = new HitBuilders.EventBuilder()
                .setAction("tv show search")
                .setCategory("search")
                .setLabel(event.getQuery().toString())
                .build();

        Tracker tracker = BestMoviesApplication.get().getDefaultTracker();
        tracker.send(hits);


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
        BestMoviesApplication.getComponent()
                .tvShow(((HomeActivity)getActivity()).getActivityModule(),
                        new TvShowModule())
                .inject(this);
    }

    @Override
    void sendAnalytics() {
        Tracker tracker = BestMoviesApplication.get().getDefaultTracker();
        tracker.setScreenName(ContentListFragment.TVSHOW_TITLE.toString());
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
