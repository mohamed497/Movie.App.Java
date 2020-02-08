package com.example.tryonetask.pojo;

import android.graphics.Movie;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Alaa Moaataz on 2020-01-22.
 */
public class ListingResponse implements Parcelable {
    public List<MovieModel> results;
    int page;
    int total_results;

    public ListingResponse(){}
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
        dest.writeInt(this.total_results);
    }

    protected ListingResponse(Parcel in) {
        this.page = in.readInt();
        this.results = in.createTypedArrayList(MovieModel.CREATOR);
        this.total_results = in.readInt();
    }

    public static final Parcelable.Creator<ListingResponse> CREATOR = new Parcelable.Creator<ListingResponse>() {
        @Override
        public ListingResponse createFromParcel(Parcel source) {
            return new ListingResponse(source);
        }

        @Override
        public ListingResponse[] newArray(int size) {
            return new ListingResponse[size];
        }
    };
}
