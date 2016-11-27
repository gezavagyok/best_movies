package org.tek.geza.bestmovies.di.module;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.tek.geza.bestmovies.model.MovieDbApi;
import org.tek.geza.bestmovies.util.network.RxErrorHandlingCallAdapterFactory;
import org.tek.geza.bestmovies.util.network.SimpleCookieJar;

import java.io.IOException;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @Provides
    HttpUrl provideHttpUrl(){
        return HttpUrl.parse("http://api.themoviedb.org/3/");
    }

    @Provides
    @Named("ApiKey")
    String providesApiKey(){
        return "0a08e38b874d0aa2d426ffc04357069d";
    }

    @Provides
    Gson provideGson(){
        GsonBuilder builder = new GsonBuilder();
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.create();
    }

    @Provides
    Converter.Factory provideConverterFactory(Gson gson){
        return GsonConverterFactory.create(gson);
    }

    @Provides
    Interceptor provideInterceptor(final @Named("ApiKey") String key){
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();
                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("api_key", key)
                        .build();
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
    }

    @Provides
    Cache provideCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(application.getCacheDir(),cacheSize);
    }

    @Provides
    CookieJar provideCookieJar(){
        return new SimpleCookieJar();
    }

    @Provides
    OkHttpClient provideOkHttpClient(CookieJar jar, Interceptor interceptor, Cache cache){
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BODY); // todo törölni
        return new OkHttpClient.Builder()
                .cookieJar(jar)
                .cache(cache)
                .addInterceptor(logger)
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory(){
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    CallAdapter.Factory provideRxErrorHandlingCallAdapterFactory(){
        return RxErrorHandlingCallAdapterFactory.create();
    }

    @Provides
    MovieDbApi provideMovieDbApi(OkHttpClient client, Converter.Factory factory, HttpUrl url, RxJavaCallAdapterFactory rxJavaCallAdapterFactory, CallAdapter.Factory errorHandler){
        return new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(factory)
                .addCallAdapterFactory(errorHandler)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build()
                .create(MovieDbApi.class);
    }
}
