package com.example.myapplication.mvx.twitter.modelAPI;

import com.example.myapplication.mvx.coin.ui.home.modelAPI.CoinList;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface PostAPI {
    @GET("posts")
    /**
     * Single is Observable
     * it emits one value or an error
     * it'will be subscribed by using two methods : onsuccess or onError
     * Single call one of those method once  and subscription to that ends after that
     */
    Single<List<Post>> getPosts();// create single observable in API
}
