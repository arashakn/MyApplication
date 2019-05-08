package com.example.myapplication.mvx.stride;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.mvx.R;
import com.example.myapplication.mvx.TwitterNext.TwitterNextPageActivity;
import com.example.myapplication.mvx.stride.adapter.RecAdapter;
import com.example.myapplication.mvx.stride.modelAPI.Item;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ActivityTwitter extends AppCompatActivity  implements  MvpTwitter.View{
    private TextView tv_twt;
    MvpTwitter.Presenter presenter;
    private RecyclerView recyclerView;
    private RecAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Item> posts = new ArrayList<>();
    private ProgressBar prgBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        setViews();
        presenter = new PresenterTwitter(this);
    }

    @Override
    public void setViews() {
        prgBar = findViewById(R.id.prgBar);
        buildRecView();
    }

    private void buildRecView() {
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager =  new LinearLayoutManager(this);
        mAdapter = new RecAdapter(posts);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                changeItem(pos,"clicked!");
            }

            @Override
            public void onDeleteClick(int pos) {
                deleteItem(pos);
            }
        });

    }

    @Override
    public void updateViews(List<Item> posts) {
        mAdapter.setRecItems(posts);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailGettingPosts() {

    }

    @Override
    public void close() {

    }

    @Override
    public void showProgressDialog(int s) {
        prgBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressDialog() {
        prgBar.setVisibility(View.INVISIBLE);

    }

    @Override
    public void nextActivity(Intent intent) {

    }
    public static Intent getIntent(Context context) {
        return  new Intent(context,ActivityTwitter.class);
    }

    public void changeItem(int pos, String text){//on Click : the Post object will be serialized to Json Object
        Item item = mAdapter.getRecItems().get(pos);
        Gson gson = new Gson();
        String serialized = gson.toJson(item);
        Intent intent= TwitterNextPageActivity.getIntent(this);
        Bundle bundle = new Bundle();
        bundle.putString(TwitterNextPageActivity.KEY_TWEET ,serialized );
        intent.putExtras(bundle);
        startActivity(intent);
    }
    public void deleteItem(int pos){
        //posts.remove(pos);
        mAdapter.notifyItemChanged(pos);
    }
}
