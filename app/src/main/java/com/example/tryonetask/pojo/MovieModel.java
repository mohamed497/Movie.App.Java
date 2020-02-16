package com.example.tryonetask.pojo;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

import androidx.annotation.Nullable;
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

    public static final Comparator<MovieModel> BY_NAME_ALPHABETICAL = new Comparator<MovieModel>() {
        @Override
        public int compare(MovieModel movie, MovieModel t1) {

            return movie.title.compareTo(t1.title);
        }
    };


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


    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.poster_path);
        dest.writeString(this.title);
        dest.writeInt(this.id);
        dest.writeString(this.overview);



    }


    protected MovieModel(Parcel in){
        this.poster_path = in.readString();
        this.overview = in.readString();
        this.id = (Integer) in.readInt();
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
    public boolean equals(@Nullable Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MovieModel other = (MovieModel) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
