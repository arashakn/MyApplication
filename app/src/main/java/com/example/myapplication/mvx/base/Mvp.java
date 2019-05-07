package com.example.myapplication.mvx.base;

import android.content.Intent;

import androidx.annotation.StringRes;

public interface Mvp {
    interface View {
        void close();
        void showProgressDialog(@StringRes int s);
        void hideProgressDialog();
        void nextActivity(Intent intent);
        void setViews();
    }

    interface Model{

    }

    interface Presenter{

    }
}
