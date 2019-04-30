package com.example.myapplication.mvx.mvvm;

import androidx.lifecycle.ViewModelProviders;
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

public class MvvmActivity extends AppCompatActivity {

    private List<String> listValues = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private ListView list;
    private CountriesViewModel viewModel;
    private Button retryButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm);
        setTitle("MVVM Activity");
        viewModel = ViewModelProviders.of(this).get(CountriesViewModel.class);

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
                Toast.makeText(MvvmActivity.this, "You clicked!" + listValues.get(position) ,Toast.LENGTH_SHORT).show();
            }
        });

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getCountries().observe(this, countries -> {
                    if(countries!=null){
                        listValues.clear();
                        listValues.addAll(countries);
                        list.setVisibility(View.VISIBLE);
                        adapter.notifyDataSetChanged();
                    }
                    else{
                        list.setVisibility(View.GONE);
                    }
                 }
                )   ;

        viewModel.getCountryError().observe(this,error ->{
            progressBar.setVisibility(View.GONE);
            if(error){
                Toast.makeText(this,"Error!", Toast.LENGTH_SHORT);
                retryButton.setVisibility(View.VISIBLE);
            }
            else{
                retryButton.setVisibility(View.GONE);
            }
        });
    }

    public static Intent getIntent(Context context){
        return new Intent(context, MvvmActivity.class);
    }

    public void onRetry(View view){
        viewModel.onRefresh();
        retryButton.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        list.setVisibility(View.GONE);
    }
}
