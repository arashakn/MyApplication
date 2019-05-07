package com.example.myapplication.mvx.twitter;


import com.example.myapplication.mvx.twitter.modelAPI.Post;
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
        modelTwitter.getPosts()
                .subscribeOn(Schedulers.newThread())//specify which thread to run the work
                .observeOn((AndroidSchedulers.mainThread()))//where to observe
                .subscribeWith(new DisposableSingleObserver<List<Post>>() {
                    @Override
                    public void onSuccess(List<Post> value) {
                        view.updateViews(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.onFailGettingPosts();

                    }
                });

    }
}
