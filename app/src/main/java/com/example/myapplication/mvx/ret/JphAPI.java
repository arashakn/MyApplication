package com.example.myapplication.mvx.ret;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JphAPI {

    @GET("posts")
    Call<List<Post>> getPost();
}
