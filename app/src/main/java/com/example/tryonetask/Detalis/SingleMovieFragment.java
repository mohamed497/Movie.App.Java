package com.example.tryonetask.Detalis;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tryonetask.Detalis.video.VideoAdapter;
import com.example.tryonetask.R;
import com.example.tryonetask.pojo.videos_data.VideoModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Alaa Moaataz on 2020-02-03.
 */
public class SingleMovieFragment extends Fragment {
    private static final String BASE_URL_IMG = "http://image.tmdb.org/t/p/w500";
    private TextView movieTitle,movieOverView ;
    private ImageView movieImg;
    DetailsViewModel detailsViewModel;
    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details_movie, container, false);


        recyclerView = view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);

//        SingleMovieActivity activity = (SingleMovieActivity) getActivity();

//        String movieTitleFromActivity = activity.getMovieTitle();
//        String movieOverViewFromActivity = activity.getMovieOverView();
//        String moviePosterFromActivity = activity.getMoviePoster();
//        int movieIdFromActivity = activity.getMovieId();

        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        detailsViewModel.getMovieTrailer(500);

        detailsViewModel.videoMovieMutableLiveData.observe(this, new Observer<List<VideoModel>>() {
            @Override
            public void onChanged(List<VideoModel> videoModels) {
                VideoAdapter videoAdapter = new VideoAdapter(view.getContext());
                videoAdapter.setList(videoModels);
                recyclerView.setAdapter(videoAdapter);
            }
        });





        Log.d("zxc","fragment : "+movieTitle);

//        movieTitle = view.findViewById(R.id.single_title);
//        movieOverView = view.findViewById(R.id.single_overView);
//        movieImg = view.findViewById(R.id.single_movieImg);

//        Glide.with(view.getContext()).load(BASE_URL_IMG+moviePosterFromActivity).into(movieImg);
//
//        movieTitle.setText(movieTitleFromActivity);
//        movieOverView.setText(movieOverViewFromActivity);


        return view;
    }

}
