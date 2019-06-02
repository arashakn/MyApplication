package com.example.myapplication.mvx.udemMV;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.myapplication.mvx.R;

public class UdemMainActivity extends AppCompatActivity implements ListFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udem_main);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.screen_container, new ListFragment()).commit();
        }
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context ,UdemMainActivity.class);
        return  intent;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
