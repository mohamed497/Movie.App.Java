package com.example.tryonetask.tryPaging;

import android.util.Log;

import com.example.tryonetask.pojo.ListingResponse;
import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.repo.Repo;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Alaa Moaataz on 2020-01-27.
 */
public class ItemDataSource extends PageKeyedDataSource<Integer, MovieModel> {

    public static final int PAGE_SIZE = 20;
    private static final int FIRST_PAGE = 1;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Repo repo = new Repo(RetrofitClient.getInstance().getApi());

//    public ItemDataSource(Repo repo) {
//        this.repo = repo;
//    }

//    public ItemDataSource() {
//
//    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, MovieModel> callback) {

//        compositeDisposable.add(RetrofitClient.getInstance()
//                .getApi().getNewPopularMovie(FIRST_PAGE,PAGE_SIZE)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BiConsumer<ListingResponse, Throwable>() {
//                    @Override
//                    public void accept(ListingResponse listingResponse, Throwable throwable) throws Exception {
//
//                        if (listingResponse != null) {
//                            callback.onResult(listingResponse.results, null, FIRST_PAGE + 1);
//                        }
//                    }
//                }));
//        Log.d("zxc",""+repo.modelSingle(FIRST_PAGE,PAGE_SIZE));
       compositeDisposable.add(repo.movieSingle(FIRST_PAGE,PAGE_SIZE)
                .subscribe(new BiConsumer<ListingResponse, Throwable>() {
                    @Override
                    public void accept(ListingResponse listingResponse, Throwable throwable) throws Exception {
                        if (listingResponse != null) {
                            callback.onResult(listingResponse.results, null, FIRST_PAGE + 1);
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
