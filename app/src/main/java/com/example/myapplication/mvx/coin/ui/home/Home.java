package com.example.myapplication.mvx.coin.ui.home;

import com.example.myapplication.mvx.base.Mvp;
import com.example.myapplication.mvx.coin.ui.home.modelAPI.CoinData;

import java.util.List;

public interface Home {

    interface  Model extends Mvp.Model{

    }
    interface Presenter extends Mvp.Presenter{
        public void fetchCryptoCoins();
        public void onFail();
        public void onSuccess();
    }
    interface View extends Mvp.View{
        public void setViewOnSuccess(List<CoinData> coins);
        public void setViewOnFail();

    }

}
