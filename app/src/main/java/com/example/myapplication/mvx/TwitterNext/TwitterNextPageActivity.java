package com.example.myapplication.mvx.TwitterNext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.myapplication.mvx.R;
import com.example.myapplication.mvx.stride.modelAPI.Item;
import com.google.gson.Gson;

public class TwitterNextPageActivity extends AppCompatActivity {
    public static final String KEY_TWEET = "KEY_TWEET";
    TextView tv_tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_next_page);
        tv_tweet = findViewById(R.id.tv_tweet);
        Intent  intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null) {
            String twtString = bundle.getString(KEY_TWEET);
            Gson gson = new Gson();
            Item post = gson.fromJson(twtString , Item.class);
            tv_tweet.setText(post.getBody());
        }

    }

    public static Intent getIntent(Context context){
        return new Intent(context ,TwitterNextPageActivity.class );
    }
}
