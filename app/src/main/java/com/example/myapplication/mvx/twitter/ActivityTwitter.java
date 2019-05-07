package com.example.myapplication.mvx.twitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.mvx.ArcActivity;
import com.example.myapplication.mvx.R;
import com.example.myapplication.mvx.twitter.modelAPI.Post;

import java.util.List;

public class ActivityTwitter extends AppCompatActivity  implements  MvpTwitter.View{
    private TextView tv_twt;
    MvpTwitter.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter);
        setViews();
        presenter = new PresenterTwitter(this);
    }

    @Override
    public void setViews() {
        tv_twt = findViewById(R.id.tv_twt);

    }

    @Override
    public void updateViews(List<Post> posts) {
        int size = posts.size();
        tv_twt.setText(String.valueOf(size));
    }

    @Override
    public void onFailGettingPosts() {

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
    public void nextActivity(Intent intent) {

    }
    public static Intent getIntent(Context context) {
        return  new Intent(context,ActivityTwitter.class);
    }
}
