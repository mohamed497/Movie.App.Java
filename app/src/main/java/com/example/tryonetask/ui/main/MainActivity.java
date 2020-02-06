package com.example.tryonetask.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tryonetask.R;
import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.tryCache.RoomViewModel;
import com.example.tryonetask.tryPaging.ItemAdapter;
import com.example.tryonetask.tryPaging.ItemViewModel;
import com.example.tryonetask.ui.ViewPager.PagerAdapter;
import com.example.tryonetask.ui.ViewPager.PopularMovieFragment;
import com.example.tryonetask.ui.ViewPager.TopMovieFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity  {

//    MovieViewModel movieViewModel;
//    MovieViewModel topMovieViewModel;

    private static final String TAG = "MainActivity";

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

//    RecyclerView recyclerView;
//    ItemAdapter adapter;
//    MovieViewModel movieViewModel;
//    public static List<MovieModel> getDataFromDB;
//    private RoomViewModel roomViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
//        movieViewModel.getTopMovie();
//        recyclerView = findViewById(R.id.recycler);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
//
//
//        if(isNetworkConnected(this)){
//            movieViewModel.topMovieMutableLiveData.observe(this, new Observer<List<MovieModel>>() {
//                @Override
//                public void onChanged(List<MovieModel> movieModels) {
//                    if(movieModels!=null) {
//                        getDataFromDB = movieModels;
//                        Log.d("zxcc","z"+getDataFromDB);
//                        MovieAdapter movieAdapter = new MovieAdapter();
//                        movieAdapter.setList(movieModels);
//                        recyclerView.setAdapter(movieAdapter);
//                    }
//                }
//            });
//        }
//        else {
//            Toast.makeText(this, "No internet found. Showing cached list in the view", Toast.LENGTH_SHORT).show();
//            roomViewModel = ViewModelProviders.of(this).get(RoomViewModel.class);
//            roomViewModel.getmAllMovie().observe(this, new Observer<List<MovieModel>>() {
//            @Override
//            public void onChanged(List<MovieModel> movieModels) {
//                MovieAdapter movieAdapter = new MovieAdapter();
//                movieAdapter.setList(movieModels);
//                recyclerView.setAdapter(movieAdapter);
//            }
//        });
//        }
//        roomViewModel = ViewModelProviders.of(this).get(RoomViewModel.class);
//        roomViewModel.getmAllMovie().observe(this, new Observer<List<MovieModel>>() {
//            @Override
//            public void onChanged(List<MovieModel> movieModels) {
//                MovieAdapter movieAdapter = new MovieAdapter();
//                movieAdapter.setList(movieModels);
//                recyclerView.setAdapter(movieAdapter);
//            }
//        });





        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.view_pager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tablLayOut);
        tabLayout.setupWithViewPager(mViewPager);




    }

    private void setupViewPager(ViewPager viewPager){
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PopularMovieFragment(),"Popular Movies");
        adapter.addFragment(new TopMovieFragment(),"Top Movies");
        viewPager.setAdapter(adapter);
    }

//    Boolean isNetworkConnected(Context context){
//        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
////        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
//        return activeNetwork != null && cm.getActiveNetworkInfo().isConnected();
//    }

}
