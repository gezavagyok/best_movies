package org.tek.geza.bestmovies;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import org.tek.geza.bestmovies.di.component.AppComponent;
import org.tek.geza.bestmovies.di.component.DaggerAppComponent;
import org.tek.geza.bestmovies.di.module.AppModule;
import org.tek.geza.bestmovies.di.module.NetworkModule;


public class BestMoviesApplication extends Application {

    private static final String PROPERTY_ID = "UA-88431812-1";
    private Tracker tracker;
    private static BestMoviesApplication application;

    /**
     * Gets the default {@link Tracker} for this {@link Application}.
     * @return tracker
     */
    synchronized public Tracker getDefaultTracker() {
        if (tracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
            tracker = analytics.newTracker(PROPERTY_ID);
        }
        return tracker;
    }

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
        application = this;
    }

    public static BestMoviesApplication get(){
        return application;
    }

    public AppComponent getAppComponent() {
        return component;
    }

    public static AppComponent getComponent() {
        return component;
    }
}
