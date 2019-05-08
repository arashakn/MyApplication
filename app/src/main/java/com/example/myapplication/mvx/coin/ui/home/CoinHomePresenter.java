package com.example.myapplication.mvx.coin.ui.home;

import com.example.myapplication.mvx.coin.ui.home.modelAPI.CoinData;
import com.example.myapplication.mvx.coin.ui.home.modelAPI.CoinList;
import com.example.myapplication.mvx.model.CountriesService;
import com.example.myapplication.mvx.model.Country;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CoinHomePresenter  implements  Home.Presenter{
    public Home.View view;
    public CoinsService model;
    private CountriesService service;


    public CoinHomePresenter(Home.View view) {
        this.view = view;
        model = new CoinsService();
        service = new CountriesService();


    }

    @Override
    public void fetchCryptoCoins() {

        model.getCoinsInfo()
                .subscribeOn(Schedulers.newThread())
                .observeOn((AndroidSchedulers.mainThread()))
                .subscribeWith(new DisposableSingleObserver<CoinList>() {
                    @Override
                    public void onSuccess(CoinList value) {
                        HashMap<String, CoinData> hm =  value.getHashMap();
                        Collection<CoinData> coins = hm.values();
                        List<CoinData> coinData =  new ArrayList<>(coins);
                         view.setViewOnSuccess(coinData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setViewOnFail();

                    }
                });

    }

    @Override
    public void onFail() {

    }

    @Override
    public void onSuccess() {

    }
}
