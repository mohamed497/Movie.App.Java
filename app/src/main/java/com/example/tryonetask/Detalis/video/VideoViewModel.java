package com.example.tryonetask.Detalis.video;

import android.app.PendingIntent;
import android.util.Log;

import com.example.tryonetask.Detalis.SingleMovieActivity;
import com.example.tryonetask.data.RetrofitClient;
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
public class VideoViewModel extends ViewModel {

    public MutableLiveData<List<VideoModel>> videoMovieMutableLiveData = new MutableLiveData<>();
    CompositeDisposable compositeDisposable = new CompositeDisposable();



    Repo repo = new Repo(RetrofitClient.getInstance().getApi());

    public void getMovieTrailer(){
        compositeDisposable.add(

                // \\ // \\ // \\
                repo.getMovieTrailer(SingleMovieActivity.movieId)
                        .subscribe(new BiConsumer<ListingVideoResponse, Throwable>() {
                            @Override
                            public void accept(ListingVideoResponse listingVideoResponse, Throwable throwable) throws Exception {
                                videoMovieMutableLiveData.postValue(listingVideoResponse.results);
                            }
                        })
        );

        Log.d("key","ID : "+SingleMovieActivity.movieId);
    }


}
