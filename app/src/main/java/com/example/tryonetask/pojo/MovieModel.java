package com.example.tryonetask.pojo;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Movies")
public class MovieModel {
@PrimaryKey(autoGenerate = true)
    public int id;
    private String title;
    private String poster_path;

    public MovieModel(String title, String poster_path) {
        this.title = title;
        this.poster_path = poster_path;
    }

    public MovieModel() {
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
