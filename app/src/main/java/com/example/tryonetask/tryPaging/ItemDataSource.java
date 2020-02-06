package com.example.tryonetask.tryPaging;

import android.util.Log;

import com.example.tryonetask.data.RetrofitClient;
import com.example.tryonetask.pojo.ListingResponse;
import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.repo.Repo;
import com.example.tryonetask.tryCache.MovieDao;
import com.example.tryonetask.tryCache.RoomViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.PageKeyedDataSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;


/**
 * Created by Alaa Moaataz on 2020-01-27.
 */
public class ItemDataSource extends PageKeyedDataSource<Integer, MovieModel> {

    public static final int PAGE_SIZE = 20;
    private static final int FIRST_PAGE = 1;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public static List<MovieModel> getMoviesToDB;

    private Repo repo = new Repo(RetrofitClient.getInstance().getApi());


    // \\
//    RoomViewModel roomViewModel = new RoomViewModel();
//
//    LiveData<List<MovieModel>> getMovies(){
//        return roomViewModel.database.MovieDao().getMovies();
//    }

//    public ItemDataSource(Repo repo) {
//        this.repo = repo;
//    }

//    public ItemDataSource() {
//
//    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, MovieModel> callback) {

       compositeDisposable.add(repo.movieSingle(FIRST_PAGE,PAGE_SIZE)
                .subscribe(new BiConsumer<ListingResponse, Throwable>() {
                    @Override
                    public void accept(ListingResponse listingResponse, Throwable throwable) throws Exception {
                        if (listingResponse != null) {
                            callback.onResult(listingResponse.results, null, FIRST_PAGE + 1);
                            Log.d("zxc","" + listingResponse.results);

                            getMoviesToDB = listingResponse.results;

                                // \\
//                            Thread(Runnable {
//                                roomViewModel.database.MovieDao().deleteAllMovies();
//                                roomViewModel.database.MovieDao().insertMovies(listingResponse.results);
//                            });

//                            Thread(Runnable {
//
//                            });
                        }
                    }
                }));

    }


    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, MovieModel> callback) {

//        compositeDisposable.add(
//                RetrofitClient.getInstance()
//                        .getApi().getNewPopularMovie(params.key,PAGE_SIZE)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new BiConsumer<ListingResponse, Throwable>() {
//                            @Override
//                            public void accept(ListingResponse listingResponse, Throwable throwable) throws Exception {
//
//                                Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
//                                if (listingResponse != null) {
//                                    callback.onResult(listingResponse.results, adjacentKey);
//                                }
//                            }
//                        }));
        compositeDisposable.add(repo.movieSingle(params.key, PAGE_SIZE)
        .subscribe(new BiConsumer<ListingResponse, Throwable>() {
            @Override
            public void accept(ListingResponse listingResponse, Throwable throwable) throws Exception {
                Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                                if (listingResponse != null) {
                                    callback.onResult(listingResponse.results, adjacentKey);
//                                    getMoviesToDB = listingResponse.results;
                                }
            }
        }));

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, MovieModel> callback) {


//        compositeDisposable.add(RetrofitClient.getInstance()
//                .getApi().getNewPopularMovie(params.key,PAGE_SIZE)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BiConsumer<ListingResponse, Throwable>() {
//                    @Override
//                    public void accept(ListingResponse listingResponse, Throwable throwable) throws Exception {
//                        if (listingResponse != null) {
////                            Integer key = listingResponse != null ? params.key + 1 : null;
//                            Integer key =  params.key + 1;
//
//                            callback.onResult(listingResponse.results, key);
//                        }
//                    }
//                }));

        compositeDisposable.add(repo.movieSingle(params.key , PAGE_SIZE)
        .subscribe(new BiConsumer<ListingResponse, Throwable>() {
            @Override
            public void accept(ListingResponse listingResponse, Throwable throwable) throws Exception {
                if (listingResponse != null) {
//                            Integer key = listingResponse != null ? params.key + 1 : null;
                            Integer key =  params.key + 1;

                            callback.onResult(listingResponse.results, key);
                        }
            }
        }));

    }

}
