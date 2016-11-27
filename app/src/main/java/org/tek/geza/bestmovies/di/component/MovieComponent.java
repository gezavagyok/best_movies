package org.tek.geza.bestmovies.di.component;

import org.tek.geza.bestmovies.di.module.ActivityModule;
import org.tek.geza.bestmovies.di.module.ui.MovieModule;
import org.tek.geza.bestmovies.view.activity.MovieDetailActivity;
import org.tek.geza.bestmovies.view.fragment.MovieFragment;

import dagger.Subcomponent;

@Subcomponent(
        modules = {
                ActivityModule.class,
                MovieModule.class
        })
public interface MovieComponent {
    void inject(MovieFragment fragment);

    void inject(MovieDetailActivity activity);
}
