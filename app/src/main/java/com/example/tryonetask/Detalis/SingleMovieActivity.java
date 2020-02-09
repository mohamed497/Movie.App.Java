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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.api.services.youtube.YouTube;
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

public class SingleMovieActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener
,YouTubePlayer.PlaybackEventListener,YouTubePlayer.PlayerStateChangeListener{



    int movieId;
    String movieTitle,overView,poster,imgPoster;
    RecyclerView recyclerView;

    MovieModel movieModel;
//    private final AppCompatActivity activity = SingleMovieActivity.this;


    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;
    Button btn;

    private static final String PREFS_TAG = "SharedPrefs";
    private static final String PRODUCT_TAG = "MyProduct";

    YouTubePlayerView playerView;
    String API_KEY = "AIzaSyAV8kimexMX2qBKmbN2zK8qgVQ5Zb1klOI";
    String VIDEO_ID = "Kopyc23VfSw";

    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";

    TextView v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_movie);

        playerView = findViewById(R.id.playerview);
        playerView.initialize(API_KEY,this);
//        recyclerView = findViewById(R.id.recycler);
//        recyclerView = findViewById(R.id.recyclerReview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setHasFixedSize(true);

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


//        Bundle intent = getIntent().getExtras();
//        if (intent != null) {
//
//            movieId = intent.getInt("MOVIE_ID");
//            overView = intent.getString("MOVIE_OVERVIEW");
//            Log.d("zxc","moview OverView : " +overView);
//            movieTitle = intent.getString("MOVIE_TITLE");
//            Log.d("zxc","movie id : "+movieId);
//            Log.d("zxc","movie title : "+movieTitle);
//
//            poster = intent.getString("MOVIE_POSTER");
//            Log.d("zxc","movie poster : "+poster);
//            imgPoster = "http://image.tmdb.org/t/p/w500"+poster;
//        }



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
                Intent intent1 = new Intent(SingleMovieActivity.this,TryReviewActivity.class);
                startActivity(intent1);
            }
        });


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


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(this);
        youTubePlayer.setPlaybackEventListener(this);

        if(!b){
            youTubePlayer.cueVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onLoaded(String s) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onVideoEnded() {

    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {

    }

    @Override
    public void onAdStarted() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onSeekTo(int i) {

    }



//    private void setupViewPager(ViewPager viewPager){
//        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
//        adapter.addFragment(new ReviewsFragment(),"Reviews");
//        adapter.addFragment(new DetailsFragment(),"Details");
//        viewPager.setAdapter(adapter);
//    }

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
