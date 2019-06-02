package com.example.myapplication.managers.charactermodel.modelAPI;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CharAPI {
    @GET("character")
    /**
     * Single is Observable
     * it emits one value or an error
     * it'will be subscribed by using two methods : onsuccess or onError
     * Single call one of those method once  and subscription to that ends after that
     */
    Single<Result> getCharacters();// create single observable in API

    @GET("character")
    /**
     */
    Call<Result> getCharactersCall();// create single observable in API
}
