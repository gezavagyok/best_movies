package org.tek.geza.bestmovies.di.component;

import org.tek.geza.bestmovies.di.module.ActivityModule;
import org.tek.geza.bestmovies.di.module.ui.PeopleModule;
import org.tek.geza.bestmovies.view.fragment.StarFragment;

import dagger.Subcomponent;

@Subcomponent(
        modules = {
                ActivityModule.class,
                PeopleModule.class
        })
public interface PeopleComponent {
    void inject(StarFragment fragment);
}
