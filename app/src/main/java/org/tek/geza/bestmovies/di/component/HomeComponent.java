package org.tek.geza.bestmovies.di.component;

import org.tek.geza.bestmovies.di.module.ActivityModule;
import org.tek.geza.bestmovies.di.module.ui.HomeModule;
import org.tek.geza.bestmovies.view.activity.HomeActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {HomeModule.class, ActivityModule.class})
public interface HomeComponent {
    void inject(HomeActivity activity);
}
