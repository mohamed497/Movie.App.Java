package com.example.tryonetask.pojo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Alaa Moaataz on 2020-02-03.
 */
//@Entity(tableName = "TopMovies")
public class TopMovieModel {
//    @PrimaryKey(autoGenerate = true)

    public int id;
    private String title;
    private String poster_path;

    public TopMovieModel(String title, String poster_path) {
        this.title = title;
        this.poster_path = poster_path;
    }


    public TopMovieModel() {
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