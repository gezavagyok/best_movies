package org.tek.geza.bestmovies.di.component;

import org.tek.geza.bestmovies.di.module.ActivityModule;
import org.tek.geza.bestmovies.di.module.ui.TvShowModule;
import org.tek.geza.bestmovies.view.activity.TvShowDetailActivity;
import org.tek.geza.bestmovies.view.fragment.TvShowFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {
        ActivityModule.class,
        TvShowModule.class
})
public interface TvShowComponent {
    void inject(TvShowFragment fragment);

    void inject(TvShowDetailActivity activity);
}
