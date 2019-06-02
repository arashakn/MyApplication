package com.example.myapplication.mvx.udemMV.model;

public class Repo {

    public final String id;
    public final String name;
    public final String desc;
    public final User user;

    public final long stargazers_count;
    public final long forks_count;

    public Repo(String id, String name, String desc, User user, long stargazers_count, long forks_count) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.user = user;
        this.stargazers_count = stargazers_count;
        this.forks_count = forks_count;
    }
}
