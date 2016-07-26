package org.tek.geza.bestmovies.view.activity;

import android.content.res.Resources;

import org.tek.geza.bestmovies.R;
import org.tek.geza.bestmovies.di.component.DaggerMovieComponent;
import org.tek.geza.bestmovies.di.component.MovieComponent;
import org.tek.geza.bestmovies.model.movie.detail.Genre;
import org.tek.geza.bestmovies.model.movie.detail.MovieDetails;
import org.tek.geza.bestmovies.model.movie.detail.ProductionCountry;
import org.tek.geza.bestmovies.presenter.MoviePresenter;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

public class MovieDetailActivity extends DetailActivity {

    MovieComponent movieComponent;

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
    protected void inject() {
        movieComponent = DaggerMovieComponent.builder()
                .activityModule(getActivityModule())
                .build();
        movieComponent.inject(this);
    }
}
