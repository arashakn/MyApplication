package com.example.myapplication.mvx.Recycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.mvx.R;

import java.util.ArrayList;

public class RecycleActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<RecItem> recItems;
    private Button btnInsert, btnRemove;
    private EditText edt_insert,edt_remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        createList();
        buildRecView();
        setupViews();

    }

    public void setupViews(){
        btnInsert = findViewById(R.id.btn_insert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.valueOf(edt_insert.getText().toString());
                insertItem(pos);
            }
        });
        btnRemove = findViewById(R.id.btn_remove);
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.valueOf(edt_remove.getText().toString());
                removeItem(pos);
            }
        });
        edt_insert = findViewById(R.id.edit_text_insert);
        edt_remove = findViewById(R.id.edit_text_remove);
    }

    public void insertItem(int pos){
        recItems.add(pos,new RecItem(R.drawable.ic_save,"new item at pos"+String.valueOf(pos),"This is line 2"));
        //mAdapter.notifyDataSetChanged();
        mAdapter.notifyItemInserted(pos);
    }

    public void removeItem(int pos){
        recItems.remove(pos);
       // mAdapter.notifyDataSetChanged();
        mAdapter.notifyItemRemoved(pos);

    }



    private void createList() {
        recItems = new ArrayList<>();
        recItems.add(new RecItem(R.drawable.ic_android,"Line 1", "Line 2"));
        recItems.add(new RecItem(R.drawable.ic_add,"Line 11", "Line 22"));
        recItems.add(new RecItem(R.drawable.ic_save,"Line 111", "Line 222"));
    }

    private void buildRecView() {
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager =  new LinearLayoutManager(this);
        mAdapter = new RecAdapter(recItems);
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
    public void changeItem(int pos, String text){
        recItems.get(pos).changeText1(text);
        mAdapter.notifyItemChanged(pos);
    }
    public void deleteItem(int pos){
        recItems.remove(pos);
        mAdapter.notifyItemChanged(pos);
    }

    public static Intent getIntent(Context context){
        return new Intent(context , RecycleActivity.class);
    }

}
