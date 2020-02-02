package com.example.tryonetask.tryCache;

import android.app.Application;

import com.example.tryonetask.pojo.MovieModel;

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



    public RoomViewModel(@NonNull Application application) {
        super(application);
        movieCacheRepo = new MovieCacheRepo(application);
        mAllMovie = movieCacheRepo.getAllMovies();
    }

    public LiveData<List<MovieModel>> getmAllMovie(){
        return mAllMovie;
    }

    public void insert(List<MovieModel> movieModel){
        movieCacheRepo.insert(movieModel);
    }


//    public MovieDatabase database = null;


//    @Override
//    public void onCreate() {
//        super.onCreate();
//        database = Room.databaseBuilder(getApplicationContext(), MovieDatabase.class, "movie_db")
//                .fallbackToDestructiveMigration().build();
//    }
}
