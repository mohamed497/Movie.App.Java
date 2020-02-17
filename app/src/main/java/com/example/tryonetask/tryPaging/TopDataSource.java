package com.example.tryonetask.tryPaging;

import android.app.Application;

import com.example.tryonetask.data.RetrofitClient;
import com.example.tryonetask.pojo.ListingResponse;
import com.example.tryonetask.pojo.ListingTopResponse;
import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.pojo.TopMovieModel;
import com.example.tryonetask.repo.Repo;
import com.example.tryonetask.tryCache.RoomViewModel;
import com.example.tryonetask.tryCache.TopRoomViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;


/**
 * Created by Alaa Moaataz on 2020-01-27.
 */
public class TopDataSource extends PageKeyedDataSource<Integer, TopMovieModel> {

    public static final int PAGE_SIZE = 20;
    private static final int FIRST_PAGE = 1;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public static List<TopMovieModel> getTopMoviesToDB;

    private Repo repo = new Repo(RetrofitClient.getInstance().getApi());
    private TopRoomViewModel topRoomViewModel = new TopRoomViewModel(new Application());


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, TopMovieModel> callback) {

        compositeDisposable.add(
                repo.topSinlgeMovie(FIRST_PAGE , PAGE_SIZE)
                        .subscribe(new BiConsumer<ListingTopResponse, Throwable>() {
                            @Override
                            public void accept(ListingTopResponse listingTopResponse, Throwable throwable) throws Exception {
                                if (listingTopResponse != null) {
                                    callback.onResult(listingTopResponse.results, null, FIRST_PAGE + 1);
                                    getTopMoviesToDB = listingTopResponse.results;
                                    topRoomViewModel.insertTop(getTopMoviesToDB);

                                }
                            }
                        })
        );


    }


    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, TopMovieModel> callback) {

        compositeDisposable.add(
                repo.topSinlgeMovie(params.key , PAGE_SIZE)
                        .subscribe(new BiConsumer<ListingTopResponse, Throwable>() {
                            @Override
                            public void accept(ListingTopResponse listingTopResponse, Throwable throwable) throws Exception {
                                Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                                if (listingTopResponse != null) {
                                    callback.onResult(listingTopResponse.results, adjacentKey);
                                }
                            }
                        })
        );



    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, TopMovieModel> callback) {

        compositeDisposable.add(
                repo.topSinlgeMovie(params.key , PAGE_SIZE)
                        .subscribe(new BiConsumer<ListingTopResponse, Throwable>() {
                            @Override
                            public void accept(ListingTopResponse listingTopResponse, Throwable throwable) throws Exception {
                                if (listingTopResponse != null) {
                                    Integer key =  params.key + 1;

                                    callback.onResult(listingTopResponse.results, key);
                                }
                            }
                        })
        );


    }


}
