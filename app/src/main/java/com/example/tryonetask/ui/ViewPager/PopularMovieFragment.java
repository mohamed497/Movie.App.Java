package com.example.tryonetask.ui.ViewPager;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.tryonetask.Detalis.SingleMovieFragment;
import com.example.tryonetask.R;
import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.tryCache.MovieDao;
import com.example.tryonetask.tryCache.MovieDatabase;
import com.example.tryonetask.tryCache.RoomViewModel;
import com.example.tryonetask.tryPaging.ItemAdapter;
import com.example.tryonetask.tryPaging.ItemDataSource;
import com.example.tryonetask.tryPaging.ItemViewModel;
import com.example.tryonetask.ui.main.MovieAdapter;
import com.example.tryonetask.ui.main.MovieViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PopularMovieFragment extends BaseFragment{

    private static final String TAG = "Tab1Fragment";
    private RoomViewModel roomViewModel;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        roomViewModel = ViewModelProviders.of(this).get(RoomViewModel.class);


        if(isNetworkConnected(view.getContext())) {

            super.itemViewModel.PopularMovies();
            super.itemViewModel.itemPagedList.observe(this, new Observer<PagedList<MovieModel>>() {
                @Override
                public void onChanged(PagedList<MovieModel> movieModels) {
                    adapter.submitList(movieModels);
                    if(movieModels != null){
                        roomViewModel.insert(movieModels);
                        Log.d("zxcc","z"+movieModels);
//                        movieDao.insertMovies(movieModels);
                    }

                }
            });
            super.recyclerView.setAdapter(super.adapter);
        }
        else {
            Toast.makeText(view.getContext(), "No internet found. Showing cached list in the view", Toast.LENGTH_SHORT).show();
            roomViewModel.getmAllMovie().observe(this, new Observer<List<MovieModel>>() {
                @Override
                public void onChanged(List<MovieModel> movieModels) {
                    MovieAdapter movieAdapter = new MovieAdapter();
                    movieAdapter.setList(movieModels);
                    Log.d("zxc","hello" + ItemDataSource.getMoviesToDB);
                    recyclerView.setAdapter(movieAdapter);
                }
            });
        }

    }

    @Override
    public View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_popular_movie , parent , false);

        return view;
    }

    @Override
    public void onStop() {
        super.itemViewModel.itemPagedList.removeObservers(this);
        super.onStop();
    }



}