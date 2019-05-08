package com.example.myapplication.mvx.stride.modelAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface PostAPI {
    @GET("character")
    /**
     * Single is Observable
     * it emits one value or an error
     * it'will be subscribed by using two methods : onsuccess or onError
     * Single call one of those method once  and subscription to that ends after that
     */
    Single<Result> getPosts();// create single observable in API
}
