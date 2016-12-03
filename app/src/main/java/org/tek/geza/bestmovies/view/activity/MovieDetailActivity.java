package org.tek.geza.bestmovies.view.activity;

import android.content.res.Resources;
import android.support.v7.widget.LinearLayoutManager;

import org.tek.geza.bestmovies.R;
import org.tek.geza.bestmovies.di.component.AppComponent;
import org.tek.geza.bestmovies.di.module.ActivityModule;
import org.tek.geza.bestmovies.di.module.ui.MovieModule;
import org.tek.geza.bestmovies.model.movie.detail.Genre;
import org.tek.geza.bestmovies.model.movie.detail.MovieDetails;
import org.tek.geza.bestmovies.model.movie.detail.ProductionCountry;
import org.tek.geza.bestmovies.presenter.MoviePresenter;
import org.tek.geza.bestmovies.view.adapter.MoviePosterAdapter;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

import static rx.plugins.RxJavaHooks.onError;

public class MovieDetailActivity extends DetailActivity {

    @Inject
    MoviePresenter moviePresenter;

    @Override
    protected void bindData(int id) {

        Subscription s = moviePresenter.getMovieDetail(id)
                .doOnNext(new Action1<MovieDetails>() {
                    @Override
                    public void call(MovieDetails movieDetails) {
                        Resources res = getResources();
                        tvTitle.setText(movieDetails.getTitle());
                        tvOriginalTitle.setText(String.format(res.getString(R.string.original_title_scheme), movieDetails.getOriginalTitle()));
                        tvYear.setText(String.format(res.getString(R.string.year_and_length_scheme), Integer.parseInt(movieDetails.getReleaseDate().substring(0,4)),movieDetails.getRuntime()));
                        tvRating.setText(movieDetails.getVoteAverage().toString());
                        tvBudget.setText(String.format(res.getString(R.string.budget_schema), movieDetails.getBudget()));
                        StringBuilder genres = new StringBuilder();
                        for(Genre g: movieDetails.getGenres()) genres.append(g.getName()).append(" ");
                        tvGenre.setText(String.format(res.getString(R.string.genre_schema), genres.toString()));
                        StringBuilder sb = new StringBuilder();
                        for(ProductionCountry pc: movieDetails.getProductionCountries()) sb.append(pc.getName()).append(" ");
                        tvCountry.setText(String.format(res.getString(R.string.production_countries_schema), sb.toString()));
                        tvOverview.setText(String.format(res.getString(R.string.story_schema), movieDetails.getOverview()));
                        llImageContainer.setLayoutManager(new LinearLayoutManager(MovieDetailActivity.this,LinearLayoutManager.HORIZONTAL,false));
                        llImageContainer.setAdapter(new MoviePosterAdapter(movieDetails.getImageUrls()));
//                        Observable.from(movieDetails.getReviews())
//                                .take(3)
//                                .doOnNext(new Action1<Review>() {
//                                    @Override
//                                    public void call(Review review) {
//                                        TextView reviewTextView = new TextView(MovieDetailActivity.this);
//                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                                        reviewTextView.setLayoutParams(params);
//                                        reviewTextView.setText(review.getContent());
//                                        llReviewContainer.addView(reviewTextView);
//                                    }
//                                }).subscribe();
                    }
                })
                .onErrorReturn(new Func1<Throwable, MovieDetails>() {
                    @Override
                    public MovieDetails call(Throwable throwable) {
                        onError(throwable);
                        return MovieDetails.error();
                    }
                })
                .subscribe();
        subscriptions.add(s);
    }

    @Override
    protected void createComponent(AppComponent appcomponent) {
        appcomponent.movie(new ActivityModule(this),new MovieModule()).inject(this);
    }
}
