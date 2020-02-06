package com.example.tryonetask.Detalis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tryonetask.Detalis.favorite_movie.FavoriteDbHelper;
import com.example.tryonetask.Detalis.reviews.ReviewAdapter;
import com.example.tryonetask.Detalis.video.VideoAdapter;
import com.example.tryonetask.Detalis.view_pager.DetailsFragment;
import com.example.tryonetask.Detalis.view_pager.ReviewsFragment;
import com.example.tryonetask.R;
import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.pojo.reviews_data.ReviewModel;
import com.example.tryonetask.pojo.videos_data.VideoModel;
import com.example.tryonetask.ui.ViewPager.PagerAdapter;
import com.example.tryonetask.ui.ViewPager.PopularMovieFragment;
import com.example.tryonetask.ui.ViewPager.TopMovieFragment;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SingleMovieActivity extends AppCompatActivity {


    int movieId;
    String movieTitle,overView,poster;
    DetailsViewModel detailsViewModel;
    RecyclerView recyclerView;


    List<MovieModel> movie;
    MovieModel movieModel;
    MovieModel favorite;
    private FavoriteDbHelper favoriteDbHelper;
    private final AppCompatActivity activity = SingleMovieActivity.this;


    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    Button btn;



    private static final String PREFS_TAG = "SharedPrefs";
    private static final String PRODUCT_TAG = "MyProduct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_movie);

//        recyclerView = findViewById(R.id.recycler);
//        recyclerView = findViewById(R.id.recyclerReview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("movies")){
            movie = getIntent().getParcelableExtra("movies");
            overView = movieModel.getOverview();
            Log.d("zxc","moview OverView : " +overView);

        }

//        Bundle intent = getIntent().getExtras();
//        if (intent != null) {
//            movieId = intent.getInt("MOVIE_ID");
//            overView = intent.getString("MOVIE_OVERVIEW");
//            Log.d("zxc","moview OverView : " +overView);
//            movieTitle = intent.getString("MOVIE_TITLE");
//            Log.d("zxc","movie id : "+movieId);
//            Log.d("zxc","movie title : "+movieTitle);
//
//            poster = intent.getString("MOVIE_POSTER");
//            Log.d("zxc","movie poster : "+poster);
//        }

//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("MOVIE_TITLE",movieTitle);
//        editor.putString("MOVIE_POSTER",poster);
//        editor.apply();






//        MaterialFavoriteButton materialFavoriteButtonNice = findViewById(R.id.favorite_button);
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        materialFavoriteButtonNice.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
//            @Override
//            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
//                if (favorite){
//                    SharedPreferences.Editor editor = getSharedPreferences("com.example.tryonetask.Detalis.SingleMovieActivity", MODE_PRIVATE).edit();
//                    editor.putBoolean("Favorite Added", true);
//                    editor.commit();
//                    saveFavorite();
//                    Snackbar.make(buttonView, "Added to Favorite",
//                            Snackbar.LENGTH_SHORT).show();
//                }else{
//                    int movie_id = getIntent().getExtras().getInt("id");
//                    favoriteDbHelper = new FavoriteDbHelper(SingleMovieActivity.this);
//                    favoriteDbHelper.deleteFavorite(movie_id);
//
//                    SharedPreferences.Editor editor = getSharedPreferences("com.example.tryonetask.Detalis.SingleMovieActivity", MODE_PRIVATE).edit();
//                    editor.putBoolean("Favorite Removed", true);
//                    editor.commit();
//                    Snackbar.make(buttonView, "Removed from Favorite",
//                            Snackbar.LENGTH_SHORT).show();
//                }
//            }
//        });

        btn = findViewById(R.id.btn_favorite);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(activity,TryReviewActivity.class);
                startActivity(intent1);
            }
        });

//        initViews();



//        key = intent.getString("MOVIE_KEY");
//        Log.d("zxc", "movie key : "+key);

//        SingleMovieFragment singleMovieFragment = new SingleMovieFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, singleMovieFragment)
//                .commit();


//        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
//        detailsViewModel.getMovieReview(movieId);
//        detailsViewModel.reviewMovieMutableLiveData.observe(this, new Observer<List<ReviewModel>>() {
//            @Override
//            public void onChanged(List<ReviewModel> reviewModels) {
//                ReviewAdapter reviewAdapter = new ReviewAdapter();
//                reviewAdapter.setList(reviewModels);
//                recyclerView.setAdapter(reviewAdapter);
//            }
//        });





//        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
//        detailsViewModel.getMovieTrailer(movieId);
//        detailsViewModel.videoMovieMutableLiveData.observe(this, new Observer<List<VideoModel>>() {
//            @Override
//            public void onChanged(List<VideoModel> videoModels) {
//                VideoAdapter videoAdapter = new  VideoAdapter();
//                videoAdapter.setList(videoModels);
//                recyclerView.setAdapter(videoAdapter);
//            }
//        });



//        Bundle bundle=new Bundle();
//        bundle.putString("message", movieTitle);
//        //set Fragmentclass Arguments
//        SingleMovieFragment fragobj=new SingleMovieFragment();
//        fragobj.setArguments(bundle);

//        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
//        mViewPager = findViewById(R.id.viewPager);
//        setupViewPager(mViewPager);
//
//        TabLayout tabLayout = findViewById(R.id.tab_lLayOut);
//        tabLayout.setupWithViewPager(mViewPager);

        setDataFromSharedPreferences(movie);

    }

    private void setDataFromSharedPreferences(List<MovieModel> movie){
        Gson gson = new Gson();
//        String jsonCurProduct = gson.toJson(movie);
        String jsonCurProduct = gson.toJson(movie);

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFS_TAG, activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putString(PRODUCT_TAG, jsonCurProduct);
        editor.commit();
    }


    public void addObject(MovieModel movie){
        Gson gson = new Gson();
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(PREFS_TAG, activity.MODE_PRIVATE);
        String jsonSaved = sharedPref.getString(PRODUCT_TAG, "");
        String jsonNewproductToAdd = gson.toJson(movie);

        JSONArray jsonArrayProduct= new JSONArray();
        if(jsonSaved.length()!=0){
            try {
                jsonArrayProduct.put(new JSONArray(jsonSaved)) ;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(PRODUCT_TAG, jsonNewproductToAdd);
        editor.commit();
    }






//    public <T> void setList(String key, List<MovieModel> list) {
//        Gson gson = new Gson();
//        String json = gson.toJson(list);
//
//        set(key, json);
//    }
//
//    public static void set(String key, String value) {
//        editor.putString(key, value);
//        editor.commit();
//    }



    private void saveFavorite() {

        favoriteDbHelper = new FavoriteDbHelper(activity);
        favorite = new MovieModel();


        favorite.id = movieId;
        favorite.setTitle(movieTitle);
        favorite.setPoster_path(poster);
        favorite.setOverview(overView);

        favoriteDbHelper.addFavorite(favorite);
    }

    private void setupViewPager(ViewPager viewPager){
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ReviewsFragment(),"Reviews");
        adapter.addFragment(new DetailsFragment(),"Details");
        viewPager.setAdapter(adapter);
    }

    public String getMovieTitle() {
        return movieTitle;
    }
    public int getMovieId() {
        return movieId;
    }
    public String getMoviePoster() {
        return poster;
    }
    public String getMovieOverView() {
        return overView;
    }
}
