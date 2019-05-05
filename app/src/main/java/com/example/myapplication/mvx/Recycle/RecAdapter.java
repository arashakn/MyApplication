package com.example.myapplication.mvx.Recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.mvx.R;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.RecViewHolder> {
    ArrayList<RecItem> recItems;
    public static class RecViewHolder extends RecyclerView.ViewHolder{
        public ImageView iv;
        public TextView tv_up;
        public TextView tv_down;

        public RecViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv_up =  itemView.findViewById(R.id.tv_up);
            tv_down =  itemView.findViewById(R.id.tv_down);
        }
    }

    public RecAdapter(ArrayList<RecItem> recItems) {
        this.recItems = recItems;
    }

    @NonNull
    @Override
    public RecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_item, parent, false);
        RecViewHolder recViewHolder =  new RecViewHolder(v);
        return recViewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull RecViewHolder holder, int position) {
        RecItem recItem = recItems.get(position);
        holder.iv.setImageResource(recItem.getmImageResource());
        holder.tv_up.setText(recItem.getmText1());
        holder.tv_down.setText(recItem.getmText2());
    }

    @Override
    public int getItemCount() {//define how many items we want to be in our list
        return recItems.size();
    }
}
