package org.tek.geza.bestmovies.di.module.ui;

import android.support.v4.app.FragmentManager;

import org.tek.geza.bestmovies.di.module.ActivityModule;
import org.tek.geza.bestmovies.view.adapter.MovieDbPager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gezacsorba on 26/11/2016.
 */
@Module(includes = ActivityModule.class)
public class HomeModule {

    @Provides
    MovieDbPager provideMovieDbPager(FragmentManager manager) {
        return new MovieDbPager(manager);
    }
}
