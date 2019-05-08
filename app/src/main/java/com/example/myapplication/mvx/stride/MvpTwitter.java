package com.example.myapplication.mvx.stride;

import com.example.myapplication.mvx.base.Mvp;
import com.example.myapplication.mvx.stride.modelAPI.Item;

import java.util.List;

public interface MvpTwitter {
    interface Model extends Mvp.Model {
    }

    interface View extends Mvp.View {
        public void setViews();
        public void updateViews(List<Item> items);
        public void onFailGettingPosts();
    }
    interface Presenter  extends Mvp.Presenter {
        public void fetchPosts();
    }
}
