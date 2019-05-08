package com.example.myapplication.mvx.stride;


import com.example.myapplication.mvx.R;
import com.example.myapplication.mvx.stride.modelAPI.Item;
import com.example.myapplication.mvx.stride.modelAPI.Result;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class PresenterTwitter implements MvpTwitter.Presenter {
    private ModelTwitter modelTwitter;
    private MvpTwitter.View view;

    public PresenterTwitter(MvpTwitter.View view) {
        this.view  = view;
        modelTwitter = new ModelTwitter();
        fetchPosts();
    }

    @Override
    public void fetchPosts() {
        view.showProgressDialog(R.string.progress_message);
        modelTwitter.getPosts()
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
