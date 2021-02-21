package com.example.tryonetask.Detalis.view_pager;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tryonetask.Detalis.DetailsViewModel;
import com.example.tryonetask.Detalis.SingleMovieActivity;
import com.example.tryonetask.Detalis.reviews.ReviewAdapter;
import com.example.tryonetask.Detalis.video.VideoAdapter;
import com.example.tryonetask.R;
import com.example.tryonetask.pojo.reviews_data.ReviewModel;


import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Alaa Moaataz on 2020-02-05.
 */
public class ReviewsFragment extends Fragment {


//    private YouTubePlayer YPlayer;


    DetailsViewModel detailsViewModel;
    RecyclerView recyclerView;
    int movieIdFromActivity;
    TextView emptyReview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_review_movie, container, false);


//        YouTubePlayerSupportFragment youTubePlayerSupportFragment = YouTubePlayerSupportFragment.newInstance();
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.replace(R.id.playerview,youTubePlayerSupportFragment).commit();


//        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.replace(R.id.youtube_fragment, youTubePlayerFragment).commit();
//        youTubePlayerFragment.initialize("key", new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//                if (!b) {
//                    YPlayer = youTubePlayer;
//                    YPlayer.setFullscreen(true);
//                    YPlayer.loadVideo("2zNSgSzhBfM");
//                    YPlayer.play();
//                }
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//            }
//        });

//        YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();

        // レイアウトにYouTubeフラグメントを追加
//        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        transaction.add(R.id.playerview, youTubePlayerFragment).commit();
//        youTubePlayerFragment.initialize("", new YouTubePlayer.OnInitializedListener() {
//            @Override
//            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
//
//            }
//
//            @Override
//            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
//
//            }
//        });

        emptyReview = view.findViewById(R.id.review_text);

        SingleMovieActivity activity = (SingleMovieActivity) getActivity();
        movieIdFromActivity = activity.getMovieId();

        recyclerView = view.findViewById(R.id.recycler_Review);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);

        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        detailsViewModel.getMovieReview(movieIdFromActivity);
        Log.d("WW", "MOVIE REVIEW : "+detailsViewModel.reviewMovieMutableLiveData);
        detailsViewModel.reviewMovieMutableLiveData.observe(this, new Observer<List<ReviewModel>>() {
            @Override
            public void onChanged(List<ReviewModel> reviewModels) {
                if (reviewModels != null){
                    ReviewAdapter reviewAdapter = new ReviewAdapter(view.getContext());
                    reviewAdapter.setList(reviewModels);
                    recyclerView.setAdapter(reviewAdapter);


                    //\\ //\\
                    emptyReview.setVisibility(View.INVISIBLE);
                }
                else {
                    Toast.makeText(view.getContext(), "THERE IS NO REVIEW !!", Toast.LENGTH_SHORT).show();
                    emptyReview.setText("THERE IS NO REVIEW !!");
                    emptyReview.setVisibility(View.VISIBLE);

                }
            }
        });
        return view;
    }
}
