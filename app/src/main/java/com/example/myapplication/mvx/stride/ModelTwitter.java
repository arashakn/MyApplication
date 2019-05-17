package com.example.myapplication.mvx.stride;

import com.example.myapplication.managers.charactermodel.modelAPI.PostAPI;
import com.example.myapplication.managers.charactermodel.modelAPI.Result;


import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModelTwitter {

    public static final  String BASE_URL = "https://rickandmortyapi.com/api/";
    private PostAPI api;
    public ModelTwitter(){ //create retrofit client here
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())// we want to pass response to GSON  to convert JSon Object to java object
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        api = retrofit.create(PostAPI.class); // Retrofit will create Java class based on API Interface
    }

    public Single<Result> getPosts(){
        return api.getCharacters();
    }
}
