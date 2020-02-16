package com.example.tryonetask.tryPaging;

import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.pojo.TopMovieModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;
import retrofit2.Retrofit;

/**
 * Created by Alaa Moaataz on 2020-01-27.
 */
public class ItemViewModel extends ViewModel {

    //creating livedata for PagedList  and PagedKeyedDataSource
    public LiveData<PagedList<MovieModel>> itemPagedList;
    public LiveData<PagedList<TopMovieModel>> topItemPagedList;

    LiveData<PageKeyedDataSource<Integer, MovieModel>> liveDataSource;
    LiveData<PageKeyedDataSource<Integer, TopMovieModel>> liveTopDataSource;





    public ItemViewModel() {
    }



    public void PopularMovies(){
        //getting our data source factory
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();

        //getting the live data source from data source factory
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        //Getting PagedList config
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE).build();

        //Building the paged list
        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
                .build();

    }

    public void TopMovies(){
        TopDataSourceFactory topDataSourceFactory = new TopDataSourceFactory();
        liveTopDataSource = topDataSourceFactory.getTopLiveDataSource();
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE).build();

        topItemPagedList = (new LivePagedListBuilder(topDataSourceFactory, pagedListConfig))
                .build();

    }




}
