package com.example.myapplication.mvx.charMvvm;

import com.example.myapplication.managers.charactermodel.modelAPI.Item;
import com.example.myapplication.managers.charactermodel.modelAPI.Result;
import com.example.myapplication.mvx.base.Mvp;

import java.util.List;

import io.reactivex.Single;

public interface MvpCharacter {
    interface Model extends Mvp.Model {
        public Single<Result> getCharacters();
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
