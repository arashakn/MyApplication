package com.example.myapplication.mvx.coin.ui.home.modelAPI;

import com.example.myapplication.mvx.model.Country;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CoinsAPI {
    @GET("data/all/coinlist")
    Single<CoinList> getCoinsInfo(); // we create a single observable in API Interface
}
