package com.example.tryonetask.Detalis.view_pager;

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
import com.example.tryonetask.Detalis.video.VideoAdapter;
import com.example.tryonetask.R;
import com.example.tryonetask.pojo.videos_data.VideoModel;
import com.example.tryonetask.ui.ViewPager.PopularMovieFragment;
import com.example.tryonetask.ui.main.MainActivity;
import com.google.android.youtube.player.YouTubeBaseActivity;

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
public class DetailsFragment extends Fragment  {
    private static final String BASE_URL_IMG = "http://image.tmdb.org/t/p/w500";
    TextView movieTitle,movieOverView ;
    ImageView movieImg;
    DetailsViewModel detailsViewModel;
    RecyclerView recyclerView;
    String movieTitleFromActivity,movieOverViewFromActivity,moviePosterFromActivity;
    int movieIdFromActivity;
    boolean mIsDualPane;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_movie, container, false);
        SingleMovieActivity activity = (SingleMovieActivity) getActivity();


        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);

        movieTitleFromActivity = activity.getMovieTitle();
        movieOverViewFromActivity = activity.getMovieOverView();
        moviePosterFromActivity = activity.getMoviePoster();
        movieIdFromActivity = activity.getMovieId();


        mIsDualPane = false;

        FrameLayout frameLayout = view.findViewById(R.id.large_details);
        if(frameLayout != null){
            mIsDualPane = frameLayout.getVisibility() == View.VISIBLE;
        }

        if(mIsDualPane){

            Toast.makeText(view.getContext(), "TABLET", Toast.LENGTH_SHORT).show();
            getChildFragmentManager().beginTransaction().replace(R.id.large_details,
                    new PopularMovieFragment()).commit();
        }
        else{
            getData(view);
        }


        Log.d("zxc","fragment : "+movieTitle);


        movieTitle = view.findViewById(R.id.single_title);
        movieOverView = view.findViewById(R.id.single_overView);
        movieImg = view.findViewById(R.id.single_movieImg);

        Glide.with(view.getContext()).load(BASE_URL_IMG+moviePosterFromActivity).into(movieImg);

        movieTitle.setText(movieTitleFromActivity);
        movieOverView.setText(movieOverViewFromActivity);

        return view;
    }

    public void getData(View view){

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
}
