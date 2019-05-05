package com.example.myapplication.mvx.Recycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.mvx.R;

import java.util.ArrayList;

public class RecycleActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        ArrayList<RecItem> recItems = new ArrayList<>();
        recItems.add(new RecItem(R.drawable.ic_android,"Line 1", "Line 2"));
        recItems.add(new RecItem(R.drawable.ic_add,"Line 11", "Line 22"));
        recItems.add(new RecItem(R.drawable.ic_save,"Line 111", "Line 222"));

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager =  new LinearLayoutManager(this);
        mAdapter = new RecAdapter(recItems);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    public static Intent getIntent(Context context){
        return new Intent(context , RecycleActivity.class);
    }

}
