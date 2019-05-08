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
    TextView tv_gender,tv_species;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twitter_next_page);
        setViews();
        Intent  intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle!=null) {
            String twtString = bundle.getString(KEY_TWEET);
            Gson gson = new Gson();
            Item post = gson.fromJson(twtString , Item.class);
            if(post != null) {
                setTitle(post.getName());
                updateViews(post);
            }
        }
    }

    public void setViews(){
        tv_gender = findViewById(R.id.tv_gender);
        tv_species = findViewById(R.id.tv_species);
    }

    public void updateViews(Item post){
        if(post.getGender()!=null) {
            tv_gender.setText(post.getGender());
        }
        if(post.getSpecies()!=null) {
            tv_species.setText(post.getSpecies());
        }
    }

    public static Intent getIntent(Context context){
        return new Intent(context ,TwitterNextPageActivity.class );
    }
}
