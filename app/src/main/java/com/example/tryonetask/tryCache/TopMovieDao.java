package com.example.tryonetask.tryCache;

import com.example.tryonetask.pojo.TopMovieModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Created by Alaa Moaataz on 2020-02-11.
 */

@Dao
public interface TopMovieDao {

    @Query("SELECT * FROM TOPMOVIES")
    LiveData<List<TopMovieModel>> getTopMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = TopMovieModel.class)
    void insertTopMovies(List<TopMovieModel> topMovieModel);

    @Query("DELETE FROM TOPMOVIES")
    void deleteAllTopMovies();
}
