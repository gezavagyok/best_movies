package org.tek.geza.bestmovies.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import org.tek.geza.bestmovies.util.SearchListener;
import org.tek.geza.bestmovies.util.network.ErrorHandler;
import org.tek.geza.bestmovies.util.network.ToastErrorHandler;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private final AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides @Named("preferencesName") String providePreferencesName(){
        return "best_movies";
    }

    @Provides AppCompatActivity provideActivity(){
        return activity;
    }

    @Provides
    Context provideContext() { return activity; }

    @Provides
    SharedPreferences provideSharedPreferences(@Named("preferencesName") String name){
        return activity.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    @Provides
    FragmentManager provideFragmentManager(){
        return activity.getSupportFragmentManager();
    }

    @Provides
    LinearLayoutManager provideLayoutManager(Context context){
        return new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
    }

    @Provides
    GridLayoutManager provideGridLayoutManager(Context context){
        final int columnCount = 2; // 2 columns
        return new GridLayoutManager(context,columnCount);
    }

    @Provides
    Application provideApplication(){
        return activity.getApplication();
    }

    @Provides
    SearchListener provideSearchListener(){
        return new SearchListener();
    }

    @Provides
    ErrorHandler provideErrorHandler() {
        return new ToastErrorHandler(activity);
    }
}
