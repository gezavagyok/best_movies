package org.tek.geza.bestmovies.di.module;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gezacsorba on 26/11/2016.
 */
@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication(){
        return application;
    }
}
