package com.example.tryonetask.repo;

import com.example.tryonetask.data.MovieClient;
import com.example.tryonetask.data.MovieInterface;
import com.example.tryonetask.pojo.ListingResponse;
import com.example.tryonetask.pojo.MovieModel;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Alaa Moaataz on 2020-01-22.
 */
public class Repo {


    private MovieInterface movieInterface;

    public Repo(MovieInterface movieInterface) {
        this.movieInterface = movieInterface;
    }

    public Single<List<MovieModel>> modelSingle(){
        return movieInterface.getPopularMovie()
                .map(new Function<ListingResponse, List<MovieModel>>() {
                    @Override
                    public List<MovieModel> apply(ListingResponse listingResponse) throws Exception {
                        return listingResponse.results;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Single<List<MovieModel>> getPopularMovies(){
        return MovieClient.movieInterface.getPopularMovie()
                .map(new Function<ListingResponse, List<MovieModel>>() {
                    @Override
                    public List<MovieModel> apply(ListingResponse listingResponse) throws Exception {
                        return listingResponse.results;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
