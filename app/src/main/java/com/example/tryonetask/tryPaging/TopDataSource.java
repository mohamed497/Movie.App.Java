package com.example.tryonetask.tryPaging;

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
public class TopDataSource extends PageKeyedDataSource<Integer, MovieModel> {

    public static final int PAGE_SIZE = 20;
    private static final int FIRST_PAGE = 1;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Repo repo = new Repo(RetrofitClient.getInstance().getApi());


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, MovieModel> callback) {

        compositeDisposable.add(
                repo.topSinlgeMovie(FIRST_PAGE , PAGE_SIZE)
                .subscribe(new BiConsumer<ListingResponse, Throwable>() {
                    @Override
                    public void accept(ListingResponse listingResponse, Throwable throwable) throws Exception {
                        if (listingResponse != null) {
                            callback.onResult(listingResponse.results, null, FIRST_PAGE + 1);
                        }
                    }
                })
        );

//        compositeDisposable.add(
//                RetrofitClient.getInstance()
//                        .getApi().getNewTopMovie(FIRST_PAGE,PAGE_SIZE)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new BiConsumer<ListingResponse, Throwable>() {
//                            @Override
//                            public void accept(ListingResponse listingResponse, Throwable throwable) throws Exception {
//
//                                if (listingResponse != null) {
//                                    callback.onResult(listingResponse.results, null, FIRST_PAGE + 1);
//                                }
//                            }
//                        }));

//                .enqueue(new Callback<ListingResponse>() {
//                    @Override
//                    public void onResponse(Call<ListingResponse> call, Response<ListingResponse> response) {
//                        if (response.body() != null) {
//                            callback.onResult(response.body().results, null, FIRST_PAGE + 1);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ListingResponse> call, Throwable t) {
//
//                    }
//                });
    }


    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, MovieModel> callback) {

        compositeDisposable.add(repo.topSinlgeMovie(params.key , PAGE_SIZE)
        .subscribe(new BiConsumer<ListingResponse, Throwable>() {
            @Override
            public void accept(ListingResponse listingResponse, Throwable throwable) throws Exception {
                Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                if (listingResponse != null) {
                    callback.onResult(listingResponse.results, adjacentKey);
                }
            }
        }));

//        compositeDisposable.add(RetrofitClient.getInstance()
//                .getApi().getNewTopMovie(params.key,PAGE_SIZE)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BiConsumer<ListingResponse, Throwable>() {
//                    @Override
//                    public void accept(ListingResponse listingResponse, Throwable throwable) throws Exception {
//
//                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
//                        if (listingResponse != null) {
//                            callback.onResult(listingResponse.results, adjacentKey);
//                        }
//                    }
//                }));

//                .enqueue(new Callback<ListingResponse>() {
//                    @Override
//                    public void onResponse(Call<ListingResponse> call, Response<ListingResponse> response) {
//                        //if the current page is greater than one
//                        //we are decrementing the page number
//                        //else there is no previous page
//                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
//                        if (response.body() != null) {
//
//                            //passing the loaded data
//                            //and the previous page key
//                            callback.onResult(response.body().results, adjacentKey);
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<ListingResponse> call, Throwable t) {
//
//                    }
//                });

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, MovieModel> callback) {

        compositeDisposable.add(repo.topSinlgeMovie(params.key , PAGE_SIZE)
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

//        compositeDisposable.add(RetrofitClient.getInstance()
//                .getApi().getNewTopMovie(params.key,PAGE_SIZE)
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

//                .enqueue(new Callback<ListingResponse>() {
//                    @Override
//                    public void onResponse(Call<ListingResponse> call, Response<ListingResponse> response) {
//
//                        if (response.body() != null) {
//                            //if the response has next page
//                            //incrementing the next page number
//                            Integer key = response.body().has_more ? params.key + 1 : null;
//                            Integer key = response.body() != null ? params.key + 1 : null;
//
//                            //passing the loaded data and next page value
//                            callback.onResult(response.body().results, key);
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ListingResponse> call, Throwable t) {
//
//                    }
//                });


    }


}
