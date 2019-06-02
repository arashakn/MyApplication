package com.example.myapplication.mvx.udemMV;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.managers.charactermodel.modelAPI.Item;
import com.example.myapplication.mvx.R;
import com.example.myapplication.mvx.udemMV.model.Repo;

import java.util.ArrayList;
import java.util.List;


public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ItemViewHolder>{

    private List<Item> data = new ArrayList<>();

    public RepoListAdapter(ListViewModel listViewModel, LifecycleOwner lifecycleOwner){
        listViewModel.getRepos().observe(lifecycleOwner, result -> {
            data.clear();
            if(result!=null){
                data.addAll(result.getResults());
                notifyDataSetChanged();
            }
        });
//        setHasStableIds(true);
    }


//    @Override
//    public long getItemId(int position) {
//    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_list_item,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(data.get(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static final class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView tv_repo_title, tv_repo_desc, tv_forks,tv_stars;
         ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_repo_title = itemView.findViewById(R.id.tv_repo_title);
            tv_repo_desc = itemView.findViewById(R.id.tv_repo_desc);
            tv_forks = itemView.findViewById(R.id.tv_forks);
            tv_stars = itemView.findViewById(R.id.tv_stars);

        }

        public void bind(Item item){
            tv_repo_title.setText(item.getName());
            tv_repo_desc.setText(item.getGender());
            tv_forks.setText(item.getSpecies());
        }
    }


}
