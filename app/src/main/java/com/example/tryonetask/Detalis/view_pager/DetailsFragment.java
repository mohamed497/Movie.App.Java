package com.example.tryonetask.Detalis.view_pager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tryonetask.Detalis.DetailsViewModel;
import com.example.tryonetask.Detalis.SingleMovieActivity;
import com.example.tryonetask.Detalis.TryReviewActivity;
import com.example.tryonetask.Detalis.reviews.ReviewAdapter;
import com.example.tryonetask.Detalis.video.VideoAdapter;
import com.example.tryonetask.R;
import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.pojo.reviews_data.ReviewModel;
import com.example.tryonetask.pojo.videos_data.VideoModel;
import com.example.tryonetask.tryPaging.ItemAdapter;
import com.example.tryonetask.tryPaging.OnTextClickListener;
import com.example.tryonetask.ui.ViewPager.PopularMovieFragment;
import com.example.tryonetask.ui.main.MainActivity;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Alaa Moaataz on 2020-02-05.
 */
public class DetailsFragment extends Fragment  implements OnTextClickListener {
    private static final String BASE_URL_IMG = "http://image.tmdb.org/t/p/w500";
    TextView movieTitle,movieOverView ;
    ImageView movieImg;
    DetailsViewModel detailsViewModel;
    RecyclerView recyclerView;
    String movieTitleFromActivity,movieOverViewFromActivity,moviePosterFromActivity;
    int movieIdFromActivity;
    boolean mIsDualPane;
    MovieModel movieModel;
    SingleMovieActivity activity;
    MaterialFavoriteButton materialFavoriteButtonNice;

    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_movie, container, false);

        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);

        movieTitle = view.findViewById(R.id.single_title);
        movieOverView = view.findViewById(R.id.single_overView);
        movieImg = view.findViewById(R.id.single_movieImg);


        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);




        mIsDualPane = false;

        FrameLayout frameLayout = view.findViewById(R.id.large_details);
        if(frameLayout != null){
            mIsDualPane = frameLayout.getVisibility() == View.VISIBLE;
        }

        if(mIsDualPane){
            OnTextClickListener x = this;

            recyclerView.setAdapter(new ItemAdapter(view.getContext(),x));


            materialFavoriteButtonNice = view.findViewById(R.id.favorite_button);

//            Toast.makeText(view.getContext(), "TABLET", Toast.LENGTH_SHORT).show();
//            getChildFragmentManager().beginTransaction().add(R.id.large_details,
//                    new PopularMovieFragment()).commit();

            Bundle bundle = this.getArguments();
            if(bundle != null){
                movieTitleFromActivity = bundle.getString("MOVIE_TITLE");
                movieOverViewFromActivity = bundle.getString("MOVIE_OVERVIEW");
                moviePosterFromActivity = bundle.getString("MOVIE_POSTER");
                movieIdFromActivity = bundle.getInt("MOVIE_ID");
                //\\ //\\
                movieModel = bundle.getParcelable("MOVIE");
                if (movieModel != null) {
                    movieModel.setTitle(movieTitleFromActivity);
                    movieModel.setPoster_path(moviePosterFromActivity);
                    movieModel.setOverview(movieOverViewFromActivity);
                    movieModel.id = movieIdFromActivity;
//                    Log.d("zzz","wwwwwww : : "+movieModel.getTitle());

                }
                Log.d("zzz","zzzzzzzzzzz : : "+movieTitleFromActivity);

            }

            getLargeData(view);

            RecyclerView recycler = view.findViewById(R.id.review_recycler);
            recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recycler.setHasFixedSize(true);

            detailsViewModel.getMovieReview(movieIdFromActivity);
            detailsViewModel.reviewMovieMutableLiveData.observe(this, new Observer<List<ReviewModel>>() {
                @Override
                public void onChanged(List<ReviewModel> reviewModels) {
                    if (reviewModels != null) {
                        materialFavoriteButtonNice.setVisibility(View.VISIBLE);
                        ReviewAdapter reviewAdapter = new ReviewAdapter(view.getContext());
                        reviewAdapter.setList(reviewModels);
                        recycler.setAdapter(reviewAdapter);
                    }
                    else{
                        movieTitle.setText("SELECT A MOVIE ! ");
                    }
                }
            });

            materialFavoriteButtonNice.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    if(favorite){
                        Snackbar.make(buttonView, "Added to Favorite",
                                Snackbar.LENGTH_SHORT).show();

                            addFavorite(view.getContext(),movieModel);
                            Intent intent = new Intent(view.getContext(),TryReviewActivity.class);
                            intent.putExtra("movie_movie",movieModel);
                            startActivity(intent);

//                        ((TryReviewActivity)getActivity()).();
                    }
                    else {
                        Snackbar.make(buttonView, "Removed from Favorite",
                                Snackbar.LENGTH_SHORT).show();
                    }
                }
            });

        }
        else{
            activity = (SingleMovieActivity) getActivity();
            movieTitleFromActivity = activity.getMovieTitle();
            movieOverViewFromActivity = activity.getMovieOverView();
            moviePosterFromActivity = activity.getMoviePoster();
            movieIdFromActivity = activity.getMovieId();

            getData(view);


        }


        Glide.with(view.getContext()).load(BASE_URL_IMG+moviePosterFromActivity).into(movieImg);

        movieTitle.setText(movieTitleFromActivity);
        movieOverView.setText(movieOverViewFromActivity);

        return view;
    }

    public void getData(View view){

        detailsViewModel.getMovieTrailer(movieIdFromActivity);
        detailsViewModel.videoMovieMutableLiveData.observe(this, new Observer<List<VideoModel>>() {
            @Override
            public void onChanged(List<VideoModel> videoModels) {
                VideoAdapter videoAdapter = new VideoAdapter(view.getContext());
                videoAdapter.setList(videoModels);
                recyclerView.setAdapter(videoAdapter);
            }
        });

    }
    public void getLargeData(View view){

        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        detailsViewModel.getMovieTrailer(movieIdFromActivity);
        detailsViewModel.videoMovieMutableLiveData.observe(this, new Observer<List<VideoModel>>() {
            @Override
            public void onChanged(List<VideoModel> videoModels) {
                VideoAdapter videoAdapter = new VideoAdapter(view.getContext());
                videoAdapter.setList(videoModels);
                recyclerView.setAdapter(videoAdapter);
            }
        });

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

    @Override
    public void onTextClick(MovieModel data) {

        Log.e("Listener", "NEW MOVIE : : " + data.getTitle());
    }
}
