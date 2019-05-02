package com.example.myapplication.mvx.ret;

import com.google.gson.annotations.SerializedName;

public class Post {

    private int userId;
    private Integer id;
    private  String title;
    @SerializedName("body")
    private String text;

    public Post(int userId, String title, String text) { // for post request - no id is required
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
