package com.example.myapplication.mvx.character;



import com.example.myapplication.managers.charactermodel.CharManager;
import com.example.myapplication.managers.charactermodel.modelAPI.Item;
import com.example.myapplication.managers.charactermodel.modelAPI.Result;
import com.example.myapplication.mvx.R;


import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class PresenterCharacter implements MvpCharacter.Presenter {
    private MvpCharacter.Model model;
    private MvpCharacter.View view;

    public PresenterCharacter(MvpCharacter.View view) {
        this.view  = view;
        model = new CharManager();
        fetchPosts();
    }

    @Override
    public void fetchPosts() {
        view.showProgressDialog(R.string.progress_message);
        model.getCharacters()
                .subscribeOn(Schedulers.newThread())//specify which thread to run the work
                .observeOn((AndroidSchedulers.mainThread()))//where to observe
                .subscribeWith(new DisposableSingleObserver<Result>() {
                    @Override
                    public void onSuccess(Result value) {
                        view.hideProgressDialog();
                        List<Item> items = value.getResults();
                        view.updateViews(items);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgressDialog();
                        view.onFailGettingPosts();
                    }
                });
    }
}
