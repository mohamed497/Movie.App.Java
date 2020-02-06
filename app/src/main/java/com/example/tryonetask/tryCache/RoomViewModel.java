package com.example.tryonetask.tryCache;

import android.app.Application;

import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.pojo.TopMovieModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

/**
 * Created by Alaa Moaataz on 2020-02-02.
 */
public class RoomViewModel extends AndroidViewModel {

    private MovieCacheRepo movieCacheRepo;
    private LiveData<List<MovieModel>> mAllMovie;
//    private LiveData<List<TopMovieModel>> mTopMovie;



    public RoomViewModel(@NonNull Application application) {
        super(application);
        movieCacheRepo = new MovieCacheRepo(application);
        mAllMovie = movieCacheRepo.getAllMovies();
//        mTopMovie = movieCacheRepo.getTopMovies();
    }

    public LiveData<List<MovieModel>> getmAllMovie(){
        return mAllMovie;
    }


    public void insert(List<MovieModel> movieModel){
        movieCacheRepo.insert(movieModel);
    }




}
