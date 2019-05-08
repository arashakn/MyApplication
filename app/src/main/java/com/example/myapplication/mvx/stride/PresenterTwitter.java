package com.example.myapplication.mvx.stride;


import com.example.myapplication.mvx.R;
import com.example.myapplication.mvx.stride.modelAPI.Item;
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
                .subscribeWith(new DisposableSingleObserver<List<Item>>() {
                    @Override
                    public void onSuccess(List<Item> value) {
                        view.hideProgressDialog();
                        view.updateViews(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.hideProgressDialog();
                        view.onFailGettingPosts();
                    }
                });
    }
}
