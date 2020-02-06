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
import com.example.tryonetask.Detalis.favorite_movie.FavoriteDbHelper;
import com.example.tryonetask.Detalis.reviews.ReviewAdapter;
import com.example.tryonetask.R;
import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.pojo.reviews_data.ReviewModel;
import com.example.tryonetask.ui.main.MovieAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
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
    private FavoriteDbHelper favoriteDbHelper;
    private MovieAdapter adapter;
    private AppCompatActivity activity = TryReviewActivity.this;
    private SwipeRefreshLayout swipeContainer;


    private static final String PREFS_TAG = "SharedPrefs";
    private static final String PRODUCT_TAG = "MyProduct";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_review);




//        getAllFavorite();


//        textTitle = findViewById(R.id.get_ttitle);
//        img = findViewById(R.id.movie_iimg);


//        recyclerView = findViewById(R.id.recyclerReview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
//
//        movieList = new ArrayList<>();
//
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        String name = preferences.getString("MOVIE_TITLE", "");
//        if(!name.equalsIgnoreCase(""))
//        {
////            Toast.makeText(TryReviewActivity.this, "MOVIE TITLE : "+name, Toast.LENGTH_SHORT).show();
//            String post = preferences.getString("MOVIE_POSTER", "");
//            MovieAdapter movieAdapter = new MovieAdapter();
//            movieList.add(new MovieModel(name,post));
//            movieAdapter.setList(movieList);
//            recyclerView.setAdapter(movieAdapter);
////            Glide.with(this).load(BASE_URL_IMG+post).into(img);
//
//        }

        getDataFromSharedPreferences();



//        initViews();
//        recyclerView = findViewById(R.id.recyclerReview);
//        MovieAdapter movieAdapter = new MovieAdapter(this);
//        movieAdapter.setList(movieList);
//        recyclerView.setAdapter(movieAdapter);


//        recyclerView = findViewById(R.id.recyclerReview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);
//
//        Bundle intent = getIntent().getExtras();
//        movieId = intent.getInt("MOVIE_ID");
//        overView = intent.getString("MOVIE_OVERVIEW");
//        Log.d("zxc","moview OverView : " +overView);
//        movieTitle = intent.getString("MOVIE_TITLE");
//        Log.d("zxc","movie id : "+movieId);
//        Log.d("zxc","movie title : "+movieTitle);
//
//        poster = intent.getString("MOVIE_POSTER");
//        Log.d("zxc","movie poster : "+poster);
//
//        key = intent.getString("MOVIE_KEY");
//        Log.d("zxc", "movie key : "+key);
//
//        detailsViewModel.getMovieReview(movieId);
//        detailsViewModel.reviewMovieMutableLiveData
//                .observe(this, new Observer<List<ReviewModel>>() {
//                    @Override
//                    public void onChanged(List<ReviewModel> reviewModels) {
//                        ReviewAdapter reviewAdapter = new ReviewAdapter();
//                        reviewAdapter.setList(reviewModels);
//                        recyclerView.setAdapter(reviewAdapter);
//                    }
//                });

    }

    private List<MovieModel> getDataFromSharedPreferences(){
        Gson gson = new Gson();
        List<MovieModel> productFromShared = new ArrayList<>();
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFS_TAG, activity.MODE_PRIVATE);
        String jsonPreferences = sharedPref.getString(PRODUCT_TAG, "");

        Type type = new TypeToken<List<MovieModel>>() {}.getType();
        productFromShared = gson.fromJson(jsonPreferences, type);

        return productFromShared;
    }

//    private void getAllFavorite(){
//        new AsyncTask<Void, Void, Void>(){
//            @Override
//            protected Void doInBackground(Void... params){
//                movieList.clear();
//                movieList.addAll(favoriteDbHelper.getAllFavorite());
//                return null;
//            }
//            @Override
//            protected void onPostExecute(Void aVoid){
//                super.onPostExecute(aVoid);
//                adapter.notifyDataSetChanged();
//            }
//        }.execute();
//    }

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


//    private void initViews(){
//
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerReview);
//
//        movieList = new ArrayList<>();
//        adapter = new MovieAdapter(this);
//        adapter.setList(movieList);
//
//
//
//        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        } else {
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        }
//
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//        favoriteDbHelper = new FavoriteDbHelper(activity);
//
//
//        swipeContainer = findViewById(R.id.main_content);
//        swipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);
//        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
//            @Override
//            public void onRefresh(){
//                initViews();
//                Toast.makeText(TryReviewActivity.this, "Movies Refreshed", Toast.LENGTH_SHORT).show();
//            }
//        });
//
////        checkSortOrder();
//
//    }

//    @Override
//    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//        checkSortOrder();
//    }

//    private void checkSortOrder() {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        String sortOrder = preferences.getString("FAVORITE"," ");
//        initViews2();
//
//    }

//    private void initViews2() {
//
//        recyclerView = findViewById(R.id.recyclerReview);
//
//        movieList = new ArrayList<>();
//        adapter = new MovieAdapter(this);
//        adapter.setList(movieList);
//
//        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        } else {
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        }
//
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//        favoriteDbHelper = new FavoriteDbHelper(activity);
//
//        getAllFavorite();
//    }
}
