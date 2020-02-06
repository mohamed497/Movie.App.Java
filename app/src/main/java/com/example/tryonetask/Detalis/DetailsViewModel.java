package com.example.tryonetask.Detalis;

import android.app.PendingIntent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.tryonetask.Detalis.SingleMovieActivity;
import com.example.tryonetask.data.RetrofitClient;
import com.example.tryonetask.pojo.reviews_data.ListingReviewResponse;
import com.example.tryonetask.pojo.reviews_data.ReviewModel;
import com.example.tryonetask.pojo.videos_data.ListingVideoResponse;
import com.example.tryonetask.pojo.videos_data.VideoModel;
import com.example.tryonetask.repo.Repo;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;

/**
 * Created by Alaa Moaataz on 2020-02-04.
 */
public class DetailsViewModel extends ViewModel {

    public MutableLiveData<List<VideoModel>> videoMovieMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<List<ReviewModel>> reviewMovieMutableLiveData = new MutableLiveData<>();

    CompositeDisposable compositeDisposable = new CompositeDisposable();



    Repo repo = new Repo(RetrofitClient.getInstance().getApi());

    public void getMovieTrailer(int id){
        Log.d("MOVIE_ID","ID : "+id);

        compositeDisposable.add(
                repo.getMovieTrailer(id)
                        .subscribe(new BiConsumer<ListingVideoResponse, Throwable>() {
                            @Override
                            public void accept(ListingVideoResponse listingVideoResponse, Throwable throwable) throws Exception {
                                if(listingVideoResponse != null){
                                    videoMovieMutableLiveData.postValue(listingVideoResponse.results);
                                }
                            }
                        })
        );

    }

    public void getMovieReview(int id){
        Log.d("MOVIE_ID","ID : "+id);
        compositeDisposable.add(
                repo.getMovieReviews(id)
                .subscribe(new BiConsumer<ListingReviewResponse, Throwable>() {
                    @Override
                    public void accept(ListingReviewResponse listingReviewResponse, Throwable throwable) throws Exception {
                        if (listingReviewResponse != null){
                            reviewMovieMutableLiveData.postValue(listingReviewResponse.results);
                        }
                    }
                })
        );
    }


}
