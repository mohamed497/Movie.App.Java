package com.example.tryonetask.Detalis.view_pager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tryonetask.Detalis.DetailsViewModel;
import com.example.tryonetask.Detalis.SingleMovieActivity;
import com.example.tryonetask.Detalis.reviews.ReviewAdapter;
import com.example.tryonetask.Detalis.video.VideoAdapter;
import com.example.tryonetask.R;
import com.example.tryonetask.pojo.reviews_data.ReviewModel;
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
 * Created by Alaa Moaataz on 2020-02-05.
 */
public class ReviewsFragment extends Fragment {


    DetailsViewModel detailsViewModel;
    RecyclerView recyclerView;
    int movieIdFromActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_review_movie, container, false);
        SingleMovieActivity activity = (SingleMovieActivity) getActivity();
        movieIdFromActivity = activity.getMovieId();

        recyclerView = view.findViewById(R.id.recycler_Review);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setHasFixedSize(true);

        detailsViewModel = ViewModelProviders.of(this).get(DetailsViewModel.class);
        detailsViewModel.getMovieReview(movieIdFromActivity);
        detailsViewModel.reviewMovieMutableLiveData.observe(this, new Observer<List<ReviewModel>>() {
            @Override
            public void onChanged(List<ReviewModel> reviewModels) {
                ReviewAdapter reviewAdapter = new ReviewAdapter(view.getContext());
                reviewAdapter.setList(reviewModels);
                recyclerView.setAdapter(reviewAdapter);
            }
        });
        return view;
    }
}
