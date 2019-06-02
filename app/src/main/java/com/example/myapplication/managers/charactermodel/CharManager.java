package com.example.myapplication.managers.charactermodel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.MyAppApplication;
import com.example.myapplication.managers.charactermodel.modelAPI.CharAPI;
import com.example.myapplication.managers.charactermodel.modelAPI.Result;
import com.example.myapplication.mvx.character.MvpCharacter;
import java.io.File;
import java.util.concurrent.TimeUnit;
import io.reactivex.Single;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharManager implements MvpCharacter.Model {
    private static  CharManager ourInstance ;

    public static synchronized  CharManager getInstance() {
        if(ourInstance == null) {
            ourInstance = new CharManager();
        }
        return ourInstance;
    }

    public static final  String BASE_URL = "https://rickandmortyapi.com/api/";
    private CharAPI api;
    private File httpCacheDirectory;
    Cache  cache;
    public CharManager(){ //create retrofit client here
        Context context = MyAppApplication.getInstance();

        /**
         * create a cache of 10 MB
         */
        httpCacheDirectory = new File(context.getCacheDir(), "httpCache");
        cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024); // create custom cache

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        /**
         * create custom http client
         */

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(chain -> {
                    try {
                        return chain.proceed(chain.request());
                    } catch (Exception e) {
                        Request offlineRequest = chain.request().newBuilder()
                                .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24)
                                .build();
                        return chain.proceed(offlineRequest);
                    }
                })
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())// we want to pass response to GSON  to convert JSon Object to java object
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();
        api = retrofit.create(CharAPI.class); // Retrofit will create Java class based on API Interface
    }

    @Override
    public Single<Result> getCharacters() {
        return api.getCharacters();
    }


    public MutableLiveData<Result> getCharactersNoRxJava() {
        final MutableLiveData<Result> data = new MutableLiveData<>();
        api.getCharactersCall().enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public CharAPI getCharApi() {
        return api;
    }
}
