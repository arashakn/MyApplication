package com.example.myapplication.mvx.Recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.mvx.R;
import com.example.myapplication.mvx.mvvmRoom.NoteAdapter;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.RecViewHolder> {
    ArrayList<RecItem> recItems;
    private OnItemClickListener listener ;

     public interface  OnItemClickListener {
         void onItemClick(int pos);
         void onDeleteClick(int pos);
     }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener  ){
         this.listener = onItemClickListener;
    }
    public static class RecViewHolder extends RecyclerView.ViewHolder{
        public ImageView iv;
        public TextView tv_up;
        public TextView tv_down;
        public  ImageView iv_delete;

        public RecViewHolder(@NonNull View itemView , final OnItemClickListener listener) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv_up =  itemView.findViewById(R.id.tv_up);
            tv_down =  itemView.findViewById(R.id.tv_down);
            iv_delete = itemView.findViewById(R.id.iv_delete);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int pos = getAdapterPosition();
                        if(pos!=RecyclerView.NO_POSITION){
                            listener.onItemClick(pos);
                        }
                    }

                }
            });

            iv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int pos = getAdapterPosition();
                        if(pos!=RecyclerView.NO_POSITION){
                            listener.onDeleteClick(pos);
                        }
                    }

                }
            });
        }
    }

    public RecAdapter(ArrayList<RecItem> recItems) {
        this.recItems = recItems;
    }

    @NonNull
    @Override
    public RecViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_item, parent, false);
        RecViewHolder recViewHolder =  new RecViewHolder(v,listener);
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
