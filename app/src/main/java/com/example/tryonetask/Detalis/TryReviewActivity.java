package com.example.tryonetask.Detalis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tryonetask.Detalis.reviews.ReviewAdapter;
import com.example.tryonetask.R;
import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.pojo.reviews_data.ReviewModel;
import com.example.tryonetask.ui.main.MainActivity;
import com.example.tryonetask.ui.main.MovieAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TryReviewActivity extends AppCompatActivity {

    public static int movieId;
    String movieTitle,overView,poster,key;
    DetailsViewModel detailsViewModel;
    RecyclerView recyclerView;

    TextView textTitle;
    ImageView img;
    private static final String BASE_URL_IMG = "http://image.tmdb.org/t/p/w500";


    private List<MovieModel> movieList;
    private MovieAdapter adapter;
    private AppCompatActivity activity = TryReviewActivity.this;

    private static final String PREFS_TAG = "SharedPrefs";
    private static final String PRODUCT_TAG = "MyProduct";


    SingleMovieActivity singleMovieActivity = new SingleMovieActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_review);


        recyclerView = findViewById(R.id.recyclerReview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
//
        movieList = new ArrayList<>();

        List<MovieModel> movie;

        movie =  singleMovieActivity.getFavorites(TryReviewActivity.this);
//        if (movie != null){
//            Log.d("zxc", "movie : "+movie.get(0));
//
//        }


        for (int i=0;i<movie.size();i++){
            String title = movie.get(i).getTitle();
            String poster = movie.get(i).getPoster_path();
            if (!checkFavoriteItem(movie.get(i))){
                movieList.add(new MovieModel(title,poster));
                adapter = new MovieAdapter();
                adapter.setList(movieList);
                recyclerView.setAdapter(adapter);
            }
            else{
                Toast.makeText(TryReviewActivity.this, "Cant Add Movie Twice", Toast.LENGTH_SHORT).show();
                Log.d("movie", "MOVIE == :"+title);
                adapter = new MovieAdapter();
                adapter.setList(movieList);
                recyclerView.setAdapter(adapter);
            }

        }

    }

    public boolean checkFavoriteItem(MovieModel checkProduct) {
        boolean check = false;
        List<MovieModel> favorites = singleMovieActivity.getFavorites(TryReviewActivity.this);
        if (favorites != null) {
            for (MovieModel movie : favorites) {
                if (movie.equals(checkProduct)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    public Activity getActivity(){
        Context context = this;
        while (context instanceof ContextWrapper){
            if (context instanceof Activity){
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;

    }





}
