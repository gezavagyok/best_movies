package org.tek.geza.bestmovies.di.component;

import org.tek.geza.bestmovies.di.module.ActivityModule;
import org.tek.geza.bestmovies.di.module.ui.HomeModule;
import org.tek.geza.bestmovies.view.activity.MainActivity;

import dagger.Subcomponent;

/**
 * Created by gezacsorba on 26/11/2016.
 */
@Subcomponent(
        modules = {
                HomeModule.class, ActivityModule.class
        })
public interface HomeActivityComponent {
    void inject(MainActivity activity);
}
