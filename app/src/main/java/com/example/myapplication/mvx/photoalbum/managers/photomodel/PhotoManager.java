package com.example.myapplication.mvx.photoalbum.managers.photomodel;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.example.myapplication.MyAppApplication;
import com.example.myapplication.mvx.bb.AboutInfo;
import com.example.myapplication.mvx.bb.AboutModelImpl;
import com.example.myapplication.mvx.photoalbum.managers.photomodel.photoAPI.GalleryImages;
import com.example.myapplication.mvx.photoalbum.managers.photomodel.photoAPI.Image;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PhotoManager {
    private static final String TAG = PhotoManager.class.getSimpleName();
    private static PhotoManager instance;
    private Context context;
    private static final String FILE_NAME = "images.json";


    public static  synchronized PhotoManager getInstance(){
        if (instance == null){
            instance = new PhotoManager();
        }return instance;
    }
    private PhotoManager(){
        this.context = MyAppApplication.getInstance();
    }


    public ArrayList<Image> getImages() {
        ArrayList<Image> images = new ArrayList<>();
        String imagesJson = getAboutInfoFromAssets();
        if(imagesJson != null && !imagesJson.isEmpty()){
            images = parseImages(imagesJson);
        }
        return images;
    }

    private ArrayList<Image> parseImages(String imagesJson) {
        Gson gson = new Gson();
        GalleryImages images = gson.fromJson(imagesJson, GalleryImages.class);
        return  images.getImages();
    }


    private String getAboutInfoFromAssets() {

        if(context != null){
            try{
                AssetManager manager = context.getAssets();
                InputStream file = manager.open(FILE_NAME);
                byte[] formArray = new byte[file.available()];
                file.read(formArray);
                file.close();
                return new String(formArray);
            }catch (IOException ex){
                Log.e(TAG, ex.getLocalizedMessage(), ex);
            }
        }

        return null;
    }

}
