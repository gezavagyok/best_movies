package org.tek.geza.bestmovies;

import android.app.Application;

import org.tek.geza.bestmovies.di.component.AppComponent;
import org.tek.geza.bestmovies.di.component.DaggerAppComponent;
import org.tek.geza.bestmovies.di.module.AppModule;
import org.tek.geza.bestmovies.di.module.NetworkModule;

/**
 * Created by gezacsorba on 26/11/2016.
 */

public class BestMoviesApplication extends Application {

    static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        if(component == null) {
            component = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .networkModule(new NetworkModule())
                    .build();
        }
    }

    public AppComponent getAppComponent() {
        return component;
    }

    public static AppComponent getComponent() {
        return component;
    }
}
