package org.tek.geza.bestmovies.view.activity;

import android.content.res.Resources;
import android.support.v7.widget.LinearLayoutManager;

import org.tek.geza.bestmovies.R;
import org.tek.geza.bestmovies.di.component.AppComponent;
import org.tek.geza.bestmovies.di.module.ActivityModule;
import org.tek.geza.bestmovies.di.module.ui.TvShowModule;
import org.tek.geza.bestmovies.model.tv.detail.CreatedBy;
import org.tek.geza.bestmovies.model.tv.detail.TvShowDetail;
import org.tek.geza.bestmovies.presenter.TvShowPresenter;
import org.tek.geza.bestmovies.view.adapter.MoviePosterAdapter;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

import static rx.plugins.RxJavaHooks.onError;

public class TvShowDetailActivity extends DetailActivity {

    @Inject
    TvShowPresenter tvShowPresenter;

    @Override
    protected void bindData(int id) {
        Subscription s = tvShowPresenter.getDetails(id)
                .doOnNext(new Action1<TvShowDetail>() {
                    @Override
                    public void call(TvShowDetail tvShowDetail) {
                        Resources res = getResources();
                        tvTitle.setText(tvShowDetail.getName());
                        tvOriginalTitle.setText(String.format(res.getString(R.string.original_title_scheme), tvShowDetail.getOriginalName()));
                        Integer length = 0;
                        if(tvShowDetail.getEpisodeRunTime().size()>0){
                            length = tvShowDetail.getEpisodeRunTime().get(0);
                        }
                        tvYear.setText(String.format(res.getString(R.string.year_and_length_scheme), Integer.parseInt(tvShowDetail.getFirstAirDate().substring(0,4)),length));
                        tvRating.setText(tvShowDetail.getVoteAverage().toString());
                        tvBudget.setText("Seasons: "+tvShowDetail.getNumberOfSeasons());
                        tvGenre.setText("Episodes: "+tvShowDetail.getNumberOfEpisodes());
                        StringBuilder sb = new StringBuilder();
                        for(CreatedBy cb: tvShowDetail.getCreatedBy()) sb.append(cb.getName()).append(" ");
                        tvCountry.setText("");
                        tvOverview.setText(String.format(res.getString(R.string.story_schema), tvShowDetail.getOverview()));
                        llImageContainer.setAdapter(new MoviePosterAdapter(tvShowDetail.getPosters()));
                        llImageContainer.setLayoutManager(new LinearLayoutManager(TvShowDetailActivity.this,LinearLayoutManager.HORIZONTAL,false));
                    }
                })
                .onErrorReturn(new Func1<Throwable, TvShowDetail>() {
                    @Override
                    public TvShowDetail call(Throwable throwable) {
                        onError(throwable);
                        return TvShowDetail.error();
                    }
                })
                .subscribe();
        subscriptions.add(s);
    }

    @Override
    protected void onStop() {
        super.onStop();
        subscriptions.unsubscribe();
    }

    @Override
    protected void createComponent(AppComponent appcomponent) {
        appcomponent.tvShow(new ActivityModule(this),new TvShowModule()).inject(this);
    }
}
