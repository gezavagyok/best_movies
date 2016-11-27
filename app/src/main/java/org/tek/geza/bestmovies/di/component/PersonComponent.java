package org.tek.geza.bestmovies.di.component;

import org.tek.geza.bestmovies.di.module.ActivityModule;
import org.tek.geza.bestmovies.di.module.ui.PersonModule;
import org.tek.geza.bestmovies.view.fragment.StarFragment;

import dagger.Subcomponent;

@Subcomponent(
        modules = {
                ActivityModule.class,
                PersonModule.class
        })
public interface PersonComponent {
    void inject(StarFragment fragment);
}
