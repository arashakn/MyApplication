package com.example.myapplication.mvx.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.mvx.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RxMainActivity extends AppCompatActivity {
    private  String greeting = "Hello from Rxjava";
    private Observable<String> myObservable;
    //Just  : returns an observable that signals given item
//    private Observer<String> myObserver;
    private TextView tv_rx;
//    private Disposable disposable;
    DisposableObserver<String> myObserver,myObserver2;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_main);
        tv_rx = findViewById(R.id.tv_rx);
        myObservable = Observable.just(greeting);
//        myObservable.subscribeOn(Schedulers.io());
//        myObservable.observeOn(AndroidSchedulers.mainThread());
//        myObserver = new Observer<String>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                disposable = d;
//
//            }
//
//            @Override
//            public void onNext(String value) {
//                tv_rx.setText(value);
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        };

        myObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String value) {
                Toast.makeText(RxMainActivity.this, value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };


//        compositeDisposable.add(myObserver);
//        myObservable.subscribe(myObserver);

        compositeDisposable.add (myObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(myObserver));

        myObserver2 = new DisposableObserver<String>() {
            @Override
            public void onNext(String value) {
                Toast.makeText(RxMainActivity.this, value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        compositeDisposable.add(myObserver2);
        myObservable.subscribe(myObserver2);
    }

    public static Intent getIntent(Context context){
        return new Intent(context, RxMainActivity.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        disposable.dispose();
        compositeDisposable.clear();

    }
}
