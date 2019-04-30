package com.example.myapplication.mvx.mvc;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.mvx.R;

import java.util.ArrayList;
import java.util.List;

public class MVCActivity extends AppCompatActivity {
    private List<String> listValues = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView list;
    private CountriesController controller;
    private Button retryButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        setTitle("MVC Activity");

        controller = new CountriesController(this);

        list = findViewById(R.id.list);
        retryButton = findViewById(R.id.retryBtn);
        progressBar = findViewById(R.id.prgBar);
        adapter = new ArrayAdapter<>(this, R.layout.row_layout,R.id.listText , listValues);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MVCActivity.this, "You clicked!" + listValues.get(position) ,Toast.LENGTH_SHORT).show();
            }
        });
//        ArrayList<String> vals = new ArrayList<>();
////        vals.add("iran");
////        vals.add("chinca");
////        vals.add("USA");
////        vals.add("Spain");
////        vals.add("India");
////        vals.add("korea");
////        vals.add("lisbun");
////        vals.add("Gerogia");
//
//        setValues(vals);

    }

    public void onRetry(View view){
        controller.onRefresh();
        retryButton.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        list.setVisibility(View.GONE);
    }

    public void setValues(List<String> values){
        listValues.clear();
        listValues.addAll(values);
        retryButton.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        list.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    public void onError(){
        Toast.makeText(this,"Unable to get countries",Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        list.setVisibility(View.GONE);
    }

    public static Intent getIntent(Context context){
        return new Intent(context,MVCActivity.class);
    }
}
