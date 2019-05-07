package com.example.myapplication.mvx.mvp;

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

public class MVPActivity extends AppCompatActivity implements CountriesPresenter.View {

    private List<String> listValues = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView list;
    private CountriesPresenter presenter;
    private Button retryButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        setTitle("Mvp Activity");

        presenter = new CountriesPresenter(this);

        list = findViewById(R.id.list);
        retryButton = findViewById(R.id.retryBtn);
        progressBar = findViewById(R.id.prgBar);
        adapter = new ArrayAdapter<>(this, R.layout.row_layout,R.id.listText , listValues);
        if(list!=null) {
            if (adapter != null) {
                list.setAdapter(adapter);
            }
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MVPActivity.this, "You clicked!" + listValues.get(position) ,Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void setValues(List<String> countries) {
        listValues.clear();
        listValues.addAll(countries);
        retryButton.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        list.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError() {
        Toast.makeText(this,"Unable to get countries",Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        list.setVisibility(View.GONE);
        retryButton.setVisibility(View.VISIBLE);

    }

    public void onRetry(View view){
        presenter.onRefresh();
        retryButton.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        list.setVisibility(View.GONE);
    }




    public static Intent getIntent(Context context){
        return new Intent(context, MVPActivity.class);
    }
}
