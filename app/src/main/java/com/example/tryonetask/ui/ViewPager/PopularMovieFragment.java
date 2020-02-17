package com.example.tryonetask.ui.ViewPager;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.tryonetask.Detalis.DetailsTestFragment;
import com.example.tryonetask.Detalis.SingleMovieActivity;
import com.example.tryonetask.Detalis.SingleMovieFragment;
import com.example.tryonetask.Detalis.TryReviewActivity;
import com.example.tryonetask.Detalis.view_pager.DetailsFragment;
import com.example.tryonetask.Detalis.view_pager.ReviewsFragment;
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
import com.google.android.material.tabs.TabLayout;

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
import androidx.viewpager.widget.ViewPager;

public class PopularMovieFragment extends BaseFragment{

    private static final String TAG = "Tab1Fragment";
    private RoomViewModel roomViewModel;

    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        roomViewModel = ViewModelProviders.of(this).get(RoomViewModel.class);


        if(isNetworkConnected(view.getContext())) {
//            if(!isTablet(view.getContext())){
                super.itemViewModel.PopularMovies();
                super.itemViewModel.itemPagedList.observe(this, new Observer<PagedList<MovieModel>>() {
                    @Override
                    public void onChanged(PagedList<MovieModel> movieModels) {
                        Log.d("ttt","Movie Size : : "+movieModels.size());
                        for (int i = 0 ; i< movieModels.size(); i++){
                        }
                        adapter.submitList(movieModels);
                        if(movieModels != null){
                            //\\ //\\ //\\
//                            roomViewModel.insert(movieModels);
//                            Log.d("zxcc","z"+movieModels);
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
//                        Log.d("zxc","hello" + ItemDataSource.getMoviesToDB);
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

//    @Override
//    public void onStop() {
//        super.itemViewModel.itemPagedList.removeObservers(this);
//        super.onStop();
//    }


    @Override
    public void onTextClick(MovieModel data) {
        Bundle bundle = new Bundle();
        bundle.putString("MOVIE_TITLE", data.getTitle());
        bundle.putString("MOVIE_OVERVIEW", data.getOverview());
        bundle.putString("MOVIE_POSTER", data.getPoster_path());
        bundle.putInt("MOVIE_ID",data.id);

        if(isTablet(getContext())){

            DetailsFragment myFrag = new DetailsFragment();
            myFrag.setArguments(bundle);

            getChildFragmentManager().beginTransaction().replace(R.id.fragment_details,
                    myFrag,"Fragment_Tag").commit();
            Toast.makeText(getActivity(), "TITLE = : "+data.getTitle(), Toast.LENGTH_SHORT).show();

        }else {
            Intent intent = new Intent(getContext(), SingleMovieActivity.class);
            intent.putExtra("new_movie",data);
            startActivity(intent);
//            DetailsTestFragment detailsTestFragment = new DetailsTestFragment();
//            detailsTestFragment.setArguments(bundle);
//            getChildFragmentManager().beginTransaction().replace(R.id.details_details
//                    ,detailsTestFragment).commit();
//            mPagerAdapter = new PagerAdapter(getChildFragmentManager());
//            mViewPager = getActivity().findViewById(R.id.viewPager);
////            mViewPager = findViewById(R.id.viewPager);
//            setupViewPager(mViewPager);


//            TabLayout tabLayout = getActivity().findViewById(R.id.tab_lLayOut);
//            TabLayout tabLayout = findViewById(R.id.tab_lLayOut);
//            tabLayout.setupWithViewPager(mViewPager);
            Toast.makeText(getActivity(), "TITLE = : "+data.getTitle(), Toast.LENGTH_SHORT).show();

        }

//        bundle.putParcelable("MOVIE",movieModel);


//        Log.d("ana", "henanaaaa ",data);

    }

    private static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

//    private void setupViewPager(ViewPager viewPager){
//        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager());
//        adapter.addFragment(new ReviewsFragment(),"Reviews");
//        adapter.addFragment(new DetailsFragment(),"Details");
//        viewPager.setAdapter(adapter);
//    }
}