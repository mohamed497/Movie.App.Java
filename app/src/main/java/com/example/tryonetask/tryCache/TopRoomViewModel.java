package com.example.tryonetask.tryCache;

import android.app.Application;

import com.example.tryonetask.pojo.TopMovieModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * Created by Alaa Moaataz on 2020-02-02.
 */
public class TopRoomViewModel extends AndroidViewModel {

    private TopMovieCacheRepo movieCacheRepo;

    private LiveData<List<TopMovieModel>> mTopMovie;



    public TopRoomViewModel(@NonNull Application application) {
        super(application);
        movieCacheRepo = new TopMovieCacheRepo(application);
        //\\
        mTopMovie = movieCacheRepo.getTopMovies();
    }


    //\\
    public void insertTop(List<TopMovieModel> topMovieModels){
        movieCacheRepo.insertTop(topMovieModels);
    }

    public LiveData<List<TopMovieModel>> getmTopMovie(){
        return mTopMovie;
    }




}
