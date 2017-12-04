package com.firrael.vote;

import android.app.Application;

import com.google.gson.Gson;

import java.lang.ref.WeakReference;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by railag on 23.11.2017.
 */

public class App extends Application {
    public static final String PREFS = "prefs";

    private static WeakReference<MainActivity> activityRef;

    private static Retrofit api;
    private static VoteService rConnectorService;

    private static Retrofit api() {
        if (api == null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);

            OkHttpClient client = httpClient.build();

            Gson gson = new Gson();

            api = new Retrofit.Builder()
                    .baseUrl(VoteService.API_ENDPOINT)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }

        return api;
    }

    public static VoteService restService() {
        if (rConnectorService == null)
            rConnectorService = createRetrofitService(VoteService.class);

        return rConnectorService;
    }

    public static void setMainActivity(MainActivity activity) {
        activityRef = new WeakReference<>(activity);
    }

    public static MainActivity getMainActivity() {
        return activityRef != null ? activityRef.get() : null;
    }

    private static <T> T createRetrofitService(final Class<T> clazz) {
        return api().create(clazz);
    }

}
