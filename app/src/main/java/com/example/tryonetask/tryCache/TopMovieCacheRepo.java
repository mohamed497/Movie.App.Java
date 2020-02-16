package com.example.tryonetask.tryCache;

import android.app.Application;
import android.os.AsyncTask;

import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.pojo.TopMovieModel;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * Created by Alaa Moaataz on 2020-02-02.
 */
public class TopMovieCacheRepo {
    private TopMovieDao movieTopDao;
    private LiveData<List<MovieModel>> mAllMovies;
    //\\
    private LiveData<List<TopMovieModel>> mTopMovies;


    public TopMovieCacheRepo(Application application) {
        TopMovieDatabase database = TopMovieDatabase.getDataBase(application);
        movieTopDao = database.MovieDao();
        mTopMovies = movieTopDao.getTopMovies();
    }



    //\\
    LiveData<List<TopMovieModel>> getTopMovies(){
        return mTopMovies;
    }

    //\\
    public void insertTop (List<TopMovieModel> movie){
        new insertTopAsyncTask(movieTopDao).execute(movie);
    }


    //\\ //\\
    private static class insertTopAsyncTask extends AsyncTask<List<TopMovieModel>, Void , Void>{

        private TopMovieDao mAsyncTaskDao;
        insertTopAsyncTask(TopMovieDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(List<TopMovieModel>... lists) {
            mAsyncTaskDao.insertTopMovies(lists[0]);
            return null;
        }
    }


}
