package com.example.tryonetask.tryCache;

import android.app.Application;
import android.os.AsyncTask;

import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.pojo.TopMovieModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Created by Alaa Moaataz on 2020-02-02.
 */
public class MovieCacheRepo {

    private MovieDao movieDao;
    private LiveData<List<MovieModel>> mAllMovies;


    public MovieCacheRepo(Application application) {

        MovieDatabase db = MovieDatabase.getDatabase(application);
        movieDao = db.MovieDao();
        mAllMovies = movieDao.getMovies();
    }

    LiveData<List<MovieModel>> getAllMovies() {
        return mAllMovies;
    }



    public void insert (List<MovieModel> movie) {
        new insertAsyncTask(movieDao).execute(movie);
    }



    private static class insertAsyncTask extends AsyncTask<List<MovieModel>, Void, Void> {

        private MovieDao mAsyncTaskDao;

        insertAsyncTask(MovieDao dao) {
            mAsyncTaskDao = dao;
        }



        @Override
        protected Void doInBackground(List<MovieModel>... lists) {

            mAsyncTaskDao.insertMovies(lists[0]);
            return null;
        }
    }


}
