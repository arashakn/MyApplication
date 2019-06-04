package com.example.myapplication.mvx.photoalbum.managers.photomodel.photoAPI;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GalleryImages {

    @SerializedName("GalleryImages")
    ArrayList<Image> images;

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }
}
