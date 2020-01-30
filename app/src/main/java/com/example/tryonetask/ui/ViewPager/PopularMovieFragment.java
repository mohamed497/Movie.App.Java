package com.example.tryonetask.ui.ViewPager;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tryonetask.R;
import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.tryPaging.ItemAdapter;
import com.example.tryonetask.tryPaging.ItemViewModel;
import com.example.tryonetask.ui.main.MovieAdapter;
import com.example.tryonetask.ui.main.MovieViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PopularMovieFragment extends BaseFragment{

//    MovieViewModel movieViewModel;
//    RecyclerView recyclerView;
//    ItemViewModel itemViewModel;

    private static final String TAG = "Tab1Fragment";



//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.fragment_popular_movie , container , false);
//
////        recyclerView = view.findViewById(R.id.recycler);
////        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
////        recyclerView.setHasFixedSize(true);
////
////        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
////
////
////        itemViewModel.PopularMovies();
////        final ItemAdapter adapter = new ItemAdapter(view.getContext());
////
////        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<MovieModel>>() {
////            @Override
////            public void onChanged(PagedList<MovieModel> movieModels) {
////                adapter.submitList(movieModels);
////            }
////        });
////
////        recyclerView.setAdapter(adapter);
//
//
//
//
//
////        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
////        movieViewModel.getPopularMovie();
////
////        recyclerView = view.findViewById(R.id.recycler);
////        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
////        recyclerView.setHasFixedSize(true);
////
////         movieViewModel.popularMovieMutableLiveData.observe(this, new Observer<List<MovieModel>>() {
////                     @Override
////                     public void onChanged(List<MovieModel> movieModels) {
////                         if(movieModels!=null) {
////                               MovieAdapter movieAdapter = new MovieAdapter();
////                               movieAdapter.setList(movieModels);
////                               recyclerView.setAdapter(movieAdapter);
////                         }
////                     }
////                 });
//        return view;
//    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.itemViewModel.PopularMovies();
        super.itemViewModel.itemPagedList.observe(this, new Observer<PagedList<MovieModel>>() {
            @Override
            public void onChanged(PagedList<MovieModel> movieModels) {
                adapter.submitList(movieModels);
            }
        });
        super.recyclerView.setAdapter(super.adapter);

    }

    @Override
    public View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_popular_movie , parent , false);
        return view;
    }


//    @Override
//    public View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.fragment_popular_movie , parent , false);
//
//
////        recyclerView = view.findViewById(R.id.recycler);
////        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
////        recyclerView.setHasFixedSize(true);
////
//        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);
//
//
////        super.itemViewModel.PopularMovies();
//        itemViewModel.PopularMovies();
//        final ItemAdapter adapter = new ItemAdapter(view.getContext());
//
//        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<MovieModel>>() {
//            @Override
//            public void onChanged(PagedList<MovieModel> movieModels) {
//                adapter.submitList(movieModels);
//            }
//        });
//
//        super.recyclerView.setAdapter(super.adapter);
////        recyclerView.setAdapter(adapter);
//        return view;
//    }

    @Override
    public void onStop() {
        itemViewModel.itemPagedList.removeObservers(this);
        super.onStop();
    }
}