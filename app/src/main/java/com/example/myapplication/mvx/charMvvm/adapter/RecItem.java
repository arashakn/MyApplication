package com.example.myapplication.mvx.charMvvm.adapter;

public class RecItem {
    private int mImageResource;
    private String mText1;
    private String mText2;

    public RecItem(int mImageResource, String mText1, String mText2) {
        this.mImageResource = mImageResource;
        this.mText1 = mText1;
        this.mText2 = mText2;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getmText1() {
        return mText1;
    }

    public String getmText2() {
        return mText2;
    }
    public void changeText1(String text){
        mText1 = text;
    }
}
