package com.example.myapplication.mvx.coin.ui.home.modelAPI;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class CoinList {
    @SerializedName("Response")
    private  String response;

    @SerializedName("Message")
    private  String message;


    @SerializedName("Data")
    private HashMap<String, CoinData> hashMap;


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap<String, CoinData> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, CoinData> hashMap) {
        this.hashMap = hashMap;
    }

}
