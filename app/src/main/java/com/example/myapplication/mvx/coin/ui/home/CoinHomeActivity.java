package com.example.myapplication.mvx.coin.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.mvx.R;
import com.example.myapplication.mvx.coin.ui.home.modelAPI.CoinData;

import java.util.List;

public class CoinHomeActivity extends AppCompatActivity  implements  Home.View{

    CoinHomePresenter presenter;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_home);
        setViews();
        presenter = new CoinHomePresenter(this);
        presenter.fetchCryptoCoins();
    }

    @Override
    public void setViews() {
        tv = findViewById(R.id.tv);
    }

    @Override
    public void close() {

    }

    @Override
    public void showProgressDialog(int s) {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void setViewOnSuccess(List<CoinData> coinDatalist) {
        int size = coinDatalist.size();
        tv.setText(String.valueOf(size));

    }

    @Override
    public void setViewOnFail() {

    }

    @Override
    public void nextActivity(Intent intent) {

    }


    public static Intent getIntent(Context context){
        return new Intent(context , CoinHomeActivity.class);
    }


}
