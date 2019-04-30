package com.example.myapplication.mvx.mvvmRoom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {
    @PrimaryKey(autoGenerate = true) // for each row we add to SQL lite table, SQLLite will increase the ID  and add it to ID column
    private int id;
    private String title; //member components
    private String description;
    private int priority;

    public Note(String title, String description, int priority) {// id will automatically generated
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
