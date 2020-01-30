package com.example.tryonetask.tryPaging;

import com.example.tryonetask.data.MovieInterface;
import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.repo.Repo;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

/**
 * Created by Alaa Moaataz on 2020-01-27.
 */
public class ItemDataSourceFactory extends DataSource.Factory {


    private MutableLiveData<PageKeyedDataSource<Integer, MovieModel>> itemLiveDataSource = new MutableLiveData<>();
    MovieInterface movieInterface;

    Repo repo = new Repo(movieInterface);


    @Override
    public DataSource<Integer , MovieModel> create() {
        ItemDataSource itemDataSource = new ItemDataSource();

        //posting the datasource to get the values
        itemLiveDataSource.postValue(itemDataSource);

        //returning the datasource
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, MovieModel>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
