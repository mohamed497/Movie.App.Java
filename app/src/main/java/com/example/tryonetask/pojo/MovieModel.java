package com.example.tryonetask.pojo;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Movies")
public class MovieModel implements Parcelable{
@PrimaryKey()
    public int id;
    private String title;
    private String poster_path;
    private String overview;

    public MovieModel(String title, String poster_path) {
        this.title = title;
        this.poster_path = poster_path;
    }


    public MovieModel() {
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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



    public static final Comparator<MovieModel> BY_NAME_ALPHABETICAL = new Comparator<MovieModel>() {
        @Override
        public int compare(MovieModel movie, MovieModel t1) {

            return movie.title.compareTo(t1.title);
        }
    };

    protected MovieModel(Parcel in){
        this.poster_path = in.readString();
        this.overview = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();

    }

    public static final Parcelable.Creator<MovieModel> CREATOR = new Parcelable.Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel source) {
            return new MovieModel(source);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.poster_path);
        dest.writeString(this.title);
        dest.writeString(this.overview);
        dest.writeValue(this.id);



    }

}
