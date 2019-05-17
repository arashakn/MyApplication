package com.example.myapplication.mvx.character;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.managers.charactermodel.modelAPI.Item;
import com.example.myapplication.mvx.R;
import com.example.myapplication.mvx.TwitterNext.TwitterNextPageActivity;
import com.example.myapplication.mvx.character.adapter.RecAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ActivityCharacters extends AppCompatActivity  implements  MvpCharacter.View{
    private TextView tv_twt;
    MvpCharacter.Presenter presenter;
    private RecyclerView recyclerView;
    private RecAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Item> posts = new ArrayList<>();
    private ProgressBar prgBar;
    private Button btn_retry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        setViews();
        presenter = new PresenterCharacter(this);
    }

    @Override
    public void setViews() {
        prgBar = findViewById(R.id.prgBar);
        btn_retry = findViewById(R.id.btn_retry);
        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(presenter!=null){
                    presenter.fetchPosts();
                }
            }
        });
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
        btn_retry.setVisibility(View.GONE);
        mAdapter.setRecItems(posts);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailGettingPosts() {
        btn_retry.setVisibility(View.VISIBLE);
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
        return  new Intent(context, ActivityCharacters.class);
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
