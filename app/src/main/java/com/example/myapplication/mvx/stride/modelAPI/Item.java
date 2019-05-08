package com.example.myapplication.mvx.stride.modelAPI;


/**
 * create Model/POJO(plain old java object) class
 */
public class Item {

//    String userId;
//    String id;
//    String title;
//    String body;
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getBody() {
//        return body;
//    }

    String name;

    String image;

    String species;

    String gender;


    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
