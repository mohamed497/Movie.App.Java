package com.example.tryonetask.ui.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tryonetask.Detalis.SingleMovieActivity;
import com.example.tryonetask.Detalis.view_pager.DetailsFragment;
import com.example.tryonetask.R;
import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.pojo.TopMovieModel;
import com.example.tryonetask.tryCache.RoomViewModel;
import com.example.tryonetask.tryCache.TopRoomViewModel;
import com.example.tryonetask.tryPaging.ItemAdapter;
import com.example.tryonetask.tryPaging.ItemDataSource;
import com.example.tryonetask.tryPaging.ItemViewModel;
import com.example.tryonetask.tryPaging.TopItemAdapter;
import com.example.tryonetask.tryPaging.TopTextClickListener;
import com.example.tryonetask.ui.main.MovieAdapter;
import com.example.tryonetask.ui.main.MovieViewModel;
import com.example.tryonetask.ui.main.TopMovieAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TopMovieFragment extends BaseFragment implements TopTextClickListener {

    private static final String TAG = "Tab2Fragment";
//    private RoomViewModel roomViewModel;

    private TopRoomViewModel roomViewModel;
    TopItemAdapter topItemAdapter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        roomViewModel = ViewModelProviders.of(this).get(TopRoomViewModel.class);

        //\\//\\
        topItemAdapter = new TopItemAdapter(view.getContext(),this);

        if(isNetworkConnected(view.getContext())) {
            super.itemViewModel.TopMovies();
            super.itemViewModel.topItemPagedList.observe(this, new Observer<PagedList<TopMovieModel>>() {
                @Override
                public void onChanged(PagedList<TopMovieModel> topMovieModels) {
                    topItemAdapter.submitList(topMovieModels);
                    if(topMovieModels != null){
                        //\\ //\\ //\\
//                        roomViewModel.insertTop(topMovieModels);
//                        movieDao.insertMovies(movieModels);
                    }
                }
            });

            recyclerView.setAdapter(topItemAdapter);

        }
        else{
            Toast.makeText(view.getContext(), "No internet found. Showing cached list in the view", Toast.LENGTH_SHORT).show();
            roomViewModel.getmTopMovie().observe(this, new Observer<List<TopMovieModel>>() {
                @Override
                public void onChanged(List<TopMovieModel> topMovieModels) {
                    TopMovieAdapter movieAdapter = new TopMovieAdapter();
                    movieAdapter.setList(topMovieModels);
//                    Log.d("zxc","hello" + ItemDataSource.getMoviesToDB);
                    recyclerView.setAdapter(movieAdapter);
                }
            });
        }

    }

    @Override
    public View provideYourFragmentView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top_movie , parent , false);


        return view;
    }

    @Override
    public void onTextClick(MovieModel data) {

    }

    @Override
    public void onTopTextClick(TopMovieModel data) {
        Bundle bundle = new Bundle();
        bundle.putString("MOVIE_TITLE", data.getTitle());
        bundle.putString("MOVIE_OVERVIEW", data.getOverview());
        bundle.putString("MOVIE_POSTER", data.getPoster_path());
        bundle.putInt("MOVIE_ID",data.id);

        if (isTablet(getContext())){
            DetailsFragment myFrag = new DetailsFragment();
            myFrag.setArguments(bundle);

            getChildFragmentManager().beginTransaction().replace(R.id.fragment_details,
                    myFrag).commit();
            Toast.makeText(getActivity(), "TITLE = : "+data.getTitle(), Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(getContext(), SingleMovieActivity.class);
            intent.putExtra("top_movie",data);
            startActivity(intent);
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


//    @Override
//    public void onStop() {
//        super.itemViewModel.topItemPagedList.removeObservers(this);
//        super.onStop();
//    }

}