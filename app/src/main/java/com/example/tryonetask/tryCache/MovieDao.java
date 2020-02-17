package com.example.tryonetask.tryCache;

import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.pojo.TopMovieModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Created by Alaa Moaataz on 2020-02-02.
 */

@Dao
public interface MovieDao {

    @Query("SELECT * FROM Movies")
    LiveData<List<MovieModel>> getMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE ,entity = MovieModel.class)
    void insertMovies(List<MovieModel> moviesList);

    @Query("DELETE FROM MOVIES")
    void deleteAllMovies();

    @Query("UPDATE Movies SET title = :newTitle & poster_path = :newPoster ")
    void update(String newTitle, String newPoster);

}
