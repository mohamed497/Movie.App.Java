package com.example.tryonetask;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.tryonetask.pojo.MovieModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Alaa Moaataz on 2020-01-27.
 */
//public class MoviesResponse implements Parcelable {
//
//
//    @SerializedName("page")
//    private int page;
//    @SerializedName("results")
//    private List<MovieModel> results;
//    @SerializedName("total_results")
//    private int totalResults;
//    @SerializedName("total_pages")
//    private int totalPages;
//
//    public int getPage() {
//        return page;
//    }
//
//    public void setPage(int page) {
//        this.page = page;
//    }
//
//    public List<MovieModel> getResults() {
//        return results;
//    }
//
//    public List<MovieModel> getMovies() {
//        return results;
//    }
//
//    public void setResults(List<MovieModel> results) {
//        this.results = results;
//    }
//
//    public void setMovies(List<MovieModel> results) {
//        this.results = results;
//    }
//
//    public int getTotalResults() {
//        return totalResults;
//    }
//
//    public void setTotalResults(int totalResults) {
//        this.totalResults = totalResults;
//    }
//
//    public int getTotalPages() {
//        return totalPages;
//    }
//
//    public void setTotalPages(int totalPages) {
//        this.totalPages = totalPages;
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(this.page);
//        dest.writeTypedList(this.results);
//        dest.writeInt(this.totalResults);
//        dest.writeInt(this.totalPages);
//    }
//
//
//    public MoviesResponse() {
//    }
//
////    protected MoviesResponse(Parcel in) {
////        this.page = in.readInt();
////        this.results = in.createTypedArrayList(Mov.CREATOR);
////        this.totalResults = in.readInt();
////        this.totalPages = in.readInt();
////    }
//
//    public static final Creator<MoviesResponse> CREATOR = new Creator<MoviesResponse>() {
//        @Override
//        public MoviesResponse createFromParcel(Parcel source) {
//            return new MoviesResponse(source);
//        }
//
//        @Override
//        public MoviesResponse[] newArray(int size) {
//            return new MoviesResponse[size];
//        }
//    };
//}
