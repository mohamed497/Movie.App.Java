package com.example.tryonetask.Detalis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryonetask.Detalis.reviews.ReviewAdapter;
import com.example.tryonetask.Detalis.video.VideoAdapter;
import com.example.tryonetask.Detalis.view_pager.DetailsFragment;
import com.example.tryonetask.Detalis.view_pager.ReviewsFragment;
import com.example.tryonetask.R;
import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.pojo.TopMovieModel;
import com.example.tryonetask.pojo.reviews_data.ReviewModel;
import com.example.tryonetask.pojo.videos_data.VideoModel;
import com.example.tryonetask.ui.ViewPager.PagerAdapter;
import com.example.tryonetask.ui.ViewPager.PopularMovieFragment;
import com.example.tryonetask.ui.ViewPager.TopMovieFragment;
import com.example.tryonetask.ui.main.MainActivity;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SingleMovieActivity extends AppCompatActivity{


//public class SingleMovieActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener
//,YouTubePlayer.PlaybackEventListener,YouTubePlayer.PlayerStateChangeListener{



    int movieId;
    String movieTitle,overView,poster,imgPoster;
    RecyclerView recyclerView;

    MovieModel movieModel;
    TopMovieModel topMovieModel;
    private final AppCompatActivity activity = SingleMovieActivity.this;


    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    Button btn;

//    YouTubePlayerView playerView;
    String API_KEY = "AIzaSyAV8kimexMX2qBKmbN2zK8qgVQ5Zb1klOI";
    String VIDEO_ID = "Kopyc23VfSw";

    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_movie);



        //youtube
//        playerView = findViewById(R.id.playerview);
//        playerView.initialize(API_KEY,this);



//        recyclerView = findViewById(R.id.recycler);
//        recyclerView = findViewById(R.id.recyclerReview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);

        Intent i = getIntent();
        if (i.hasExtra("new_movie")){
            MovieModel movieModel;
            movieModel = getIntent().getParcelableExtra("new_movie");
            movieTitle = movieModel.getTitle();
            overView = movieModel.getOverview();
            poster = movieModel.getPoster_path();
            movieId = movieModel.id;

//            Log.d("zxczxc", "MOVIEEEEEE :: "+z);

        }

        Intent ii = getIntent();
        if (ii.hasExtra("top_movie")){
            TopMovieModel topMovieModel;
            topMovieModel = getIntent().getParcelableExtra("top_movie");
            movieTitle = topMovieModel.getTitle();
            overView = topMovieModel.getOverview();
            poster = topMovieModel.getPoster_path();
            movieId = topMovieModel.id;

//            Log.d("zxczxc", "MOVIEEEEEE :: "+z);

        }

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("movie")){
            movieModel = getIntent().getParcelableExtra("movie");
            movieId = movieModel.id;
            movieTitle = movieModel.getTitle();
            poster = movieModel.getPoster_path();
            overView = movieModel.getOverview();
            Log.d("zxc","moview OverView : " +overView);
            Log.d("zxc","movie title : "+movieTitle);
            Log.d("zxc","movie id : "+movieId);

        }
        else{
            Toast.makeText(this, "No API Data", Toast.LENGTH_SHORT).show();
        }


//        boolean mIsDualPane = false;
//        FrameLayout frameLayout = findViewById(R.id.frame_container);
//        if(frameLayout != null){
//            mIsDualPane = frameLayout.getVisibility() == View.VISIBLE;
//        }
//
//        if (mIsDualPane){
//
//        }

        boolean x = false;

        FrameLayout frameLayout = findViewById(R.id.frame_container);
        if (frameLayout != null){
            x = frameLayout.getVisibility() == View.VISIBLE;
        }
        if(x){
            Bundle bundle = new Bundle();
            bundle.putString("MOVIE_TITLE", movieTitle);
            bundle.putString("MOVIE_OVERVIEW", overView);
            bundle.putString("MOVIE_POSTER", poster);
            bundle.putInt("MOVIE_ID",movieId);
            DetailsFragment myFrag = new DetailsFragment();
            myFrag.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,myFrag).commit();

        }
//        else{
//
//        }


//        MaterialFavoriteButton materialFavoriteButtonNice = findViewById(R.id.favorite_button);
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        materialFavoriteButtonNice.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
//            @Override
//            public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
//                if (favorite){
//                    Snackbar.make(buttonView, "Added to Favorite",Snackbar.LENGTH_SHORT).show();
//                    Intent intent = new Intent(SingleMovieActivity.this,TryReviewActivity.class);
//                    startActivity(intent);
//                }else{
//                    Snackbar.make(buttonView, "Removed from Favorite",Snackbar.LENGTH_SHORT).show();
//                    removeFavorite(activity, movieModel);
////                    Intent intent = new Intent(SingleMovieActivity.this,TryReviewActivity.class);
////                    startActivity(intent);
//                }
//            }
//        });

//        btn = findViewById(R.id.btn_favorite);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent1 = new Intent(SingleMovieActivity.this,TryReviewActivity.class);
//                startActivity(intent1);
//            }
//        });


//        SingleMovieFragment singleMovieFragment = new SingleMovieFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, singleMovieFragment)
//                .commit();


        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.viewPager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tab_lLayOut);
        tabLayout.setupWithViewPager(mViewPager);


        addFavorite(SingleMovieActivity.this,movieModel);
    }

    public void saveFavorites(Context context, List<MovieModel> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, MovieModel movie) {
        List<MovieModel> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<MovieModel>();
        favorites.add(movie);
        saveFavorites(context, favorites);
    }
    public void removeFavorite(Context context, MovieModel movie) {
        ArrayList<MovieModel> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(movie);
            saveFavorites(context, favorites);
        }
    }


    public ArrayList<MovieModel> getFavorites(Context context) {
        SharedPreferences settings;
        List<MovieModel> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();

                MovieModel[] favoriteItems = gson.fromJson(jsonFavorites,
                        MovieModel[].class);
                favorites = Arrays.asList(favoriteItems);
                favorites = new ArrayList<MovieModel>(favorites);

        } else
            return null;

        return (ArrayList<MovieModel>) favorites;

    }

    // youtube

//    @Override
//    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//        youTubePlayer.setPlayerStateChangeListener(this);
//        youTubePlayer.setPlaybackEventListener(this);
//
//        if(!b){
//            youTubePlayer.cueVideo(VIDEO_ID);
//        }
//    }
//
//    @Override
//    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//    }
//
//    @Override
//    public void onPlaying() {
//
//    }
//
//    @Override
//    public void onLoaded(String s) {
//
//    }
//
//    @Override
//    public void onLoading() {
//
//    }
//
//    @Override
//    public void onVideoStarted() {
//
//    }
//
//    @Override
//    public void onVideoEnded() {
//
//    }
//
//    @Override
//    public void onError(YouTubePlayer.ErrorReason errorReason) {
//
//    }
//
//    @Override
//    public void onAdStarted() {
//
//    }
//
//    @Override
//    public void onBuffering(boolean b) {
//
//    }
//
//    @Override
//    public void onStopped() {
//
//    }
//
//    @Override
//    public void onPaused() {
//
//    }
//
//    @Override
//    public void onSeekTo(int i) {
//
//    }



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
