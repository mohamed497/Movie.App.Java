package com.example.tryonetask.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Alaa Moaataz on 2020-02-11.
 */
public class ListingTopResponse implements Parcelable{
    public List<TopMovieModel> results;
    int page;
    int total_results;

    public ListingTopResponse(){}
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeTypedList(this.results);
//        dest.writeList(this.results);
        dest.writeInt(this.total_results);
    }

    protected ListingTopResponse(Parcel in) {
        this.page = in.readInt();
        this.results = in.createTypedArrayList(TopMovieModel.CREATOR);
//        this.results = new ArrayList<>();
//        in.readList(this.results, MovieModel.class.getClassLoader());
        this.total_results = in.readInt();
    }

    public static final Creator<ListingTopResponse> CREATOR = new Creator<ListingTopResponse>() {
        @Override
        public ListingTopResponse createFromParcel(Parcel source) {
            return new ListingTopResponse(source);
        }

        @Override
        public ListingTopResponse[] newArray(int size) {
            return new ListingTopResponse[size];
        }
    };
}
