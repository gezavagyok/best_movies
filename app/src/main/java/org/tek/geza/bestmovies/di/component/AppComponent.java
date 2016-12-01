package org.tek.geza.bestmovies.di.component;

import org.tek.geza.bestmovies.di.module.ActivityModule;
import org.tek.geza.bestmovies.di.module.AppModule;
import org.tek.geza.bestmovies.di.module.NetworkModule;
import org.tek.geza.bestmovies.di.module.ui.HomeModule;
import org.tek.geza.bestmovies.di.module.ui.MovieModule;
import org.tek.geza.bestmovies.di.module.ui.PeopleModule;
import org.tek.geza.bestmovies.di.module.ui.TvShowModule;

import dagger.Component;

/**
 * Created by gezacsorba on 26/11/2016.
 */
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    HomeComponent createHome(HomeModule homeModule,
                             ActivityModule module);

    MovieComponent movie(ActivityModule module,
                         MovieModule movieModule);

    PeopleComponent person(ActivityModule module,
                           PeopleModule peopleModule);

    TvShowComponent tvShow(ActivityModule module,
                           TvShowModule tvShowModule);
}
