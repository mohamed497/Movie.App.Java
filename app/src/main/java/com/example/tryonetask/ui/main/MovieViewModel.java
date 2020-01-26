package com.example.tryonetask.ui.main;

import android.util.Log;

import com.example.tryonetask.data.MovieClient;
import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.repo.Repo;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;

public class MovieViewModel extends ViewModel {


    //list of movie return from retrofit
    public MutableLiveData<List<MovieModel>> popularMovieMutableLiveData = new MutableLiveData<>();


    public MutableLiveData<List<MovieModel>> topMovieMutableLiveData = new MutableLiveData<>();


//    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    // get movie from retrofit
    public void getPopularMovie() {

        //  Repo.getPopularMovies()
        MovieClient.getInstance().getPopularMovies()
                .subscribe(new BiConsumer<List<MovieModel>, Throwable>() {
                    @Override
                    public void accept(List<MovieModel> movieModels, Throwable throwable) throws Exception {
                        popularMovieMutableLiveData.postValue(movieModels);
                        Log.d("movies", "" + movieModels.size());
                    }
                });

    }


//    public void loadData(){
//        Repo.getPopularMovies()
//                .subscribe(new BiConsumer<List<MovieModel>, Throwable>() {
//                    @Override
//                    public void accept(List<MovieModel> movieModels, Throwable throwable) throws Exception {
//                        popularMovieMutableLiveData.postValue(movieModels);
//                    }
//                });
//    }


    public void getTopMovie() {
        MovieClient.getInstance().getTopMovies()
                .subscribe(new BiConsumer<List<MovieModel>, Throwable>() {
                    @Override
                    public void accept(List<MovieModel> movieModels, Throwable throwable) throws Exception {
                        topMovieMutableLiveData.postValue(movieModels);
                    }
                });

    }


}
