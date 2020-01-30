package com.example.tryonetask.tryPaging;

import com.example.tryonetask.pojo.MovieModel;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

/**
 * Created by Alaa Moaataz on 2020-01-27.
 */
public class TopDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, MovieModel>> TopLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer , MovieModel> create() {
        TopDataSource topDataSource = new TopDataSource();

        //posting the datasource to get the values
        TopLiveDataSource.postValue(topDataSource);

        //returning the datasource
        return topDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, MovieModel>> getTopLiveDataSource() {
        return TopLiveDataSource;
    }
}
