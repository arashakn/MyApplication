package com.example.myapplication.mvx.coin.ui.home;

import com.example.myapplication.mvx.coin.ui.home.modelAPI.CoinData;
import com.example.myapplication.mvx.coin.ui.home.modelAPI.CoinList;
import com.example.myapplication.mvx.coin.ui.home.modelAPI.CoinsAPI;
import com.example.myapplication.mvx.model.CountriesAPI;
import com.example.myapplication.mvx.model.Country;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoinsService {
    public static final  String BASE_URL = "https://min-api.cryptocompare.com/";
    private CoinsAPI api;
    public CoinsService(){ //create retrofit client here
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api = retrofit.create(CoinsAPI.class);
    }

    public Single<CoinList> getCoinsInfo(){
        return api.getCoinsInfo();
    }
}
