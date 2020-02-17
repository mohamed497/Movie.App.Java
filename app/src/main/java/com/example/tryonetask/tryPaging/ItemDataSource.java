package com.example.tryonetask.tryPaging;

import android.app.Application;
import android.content.Context;
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
import androidx.lifecycle.ViewModelProviders;
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

    private RoomViewModel roomViewModel = new RoomViewModel(new Application());
//    private Context context;
//
//
//    public ItemDataSource(Context context){
//        this.context=context;
//    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, MovieModel> callback) {

       compositeDisposable.add(repo.movieSingle(FIRST_PAGE,PAGE_SIZE)
                .subscribe(new BiConsumer<ListingResponse, Throwable>() {
                    @Override
                    public void accept(ListingResponse listingResponse, Throwable throwable) throws Exception {
                        if (listingResponse != null) {
                            callback.onResult(listingResponse.results, null, FIRST_PAGE + 1);
                            Log.d("zxc","ZZ : " + listingResponse.results);

//                            for (int i= 0; i<listingResponse.results.size()){
//                                roomViewModel.
//                            }
                            getMoviesToDB = listingResponse.results;
                            Log.d("RRR", "SIZE SIZE : "+listingResponse.results.size());
//                            listingResponse.results.get(i).
                            roomViewModel.insert(getMoviesToDB);

                        }
                    }
                }));

    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, MovieModel> callback) {

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
