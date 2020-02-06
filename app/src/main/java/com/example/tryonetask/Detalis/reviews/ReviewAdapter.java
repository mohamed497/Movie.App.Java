package com.example.tryonetask.Detalis.reviews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tryonetask.R;
import com.example.tryonetask.pojo.reviews_data.ListingReviewResponse;
import com.example.tryonetask.pojo.reviews_data.ReviewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Alaa Moaataz on 2020-02-05.
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private Context context;
    private List<ReviewModel> reviewModels;
    private ListingReviewResponse listingReviewResponse;

    public ReviewAdapter() {
    }

    public ReviewAdapter(Context context) {
        this.context = context;
        reviewModels = new ArrayList<>();
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {

        listingReviewResponse = new ListingReviewResponse();

        ReviewModel reviewModel = reviewModels.get(position);

        if(listingReviewResponse.total_results == 0){
            holder.authorName.setText(reviewModel.getAuthor());
            holder.movieContent.setText(reviewModel.getContent());

            Log.d("author_name","author name : "+reviewModel.getAuthor());
        }
        else {
            holder.authorName.setText("THERE IS NO REVIEW !!");
        }


    }

    public void setList(List<ReviewModel> reviewModels){
        this.reviewModels = reviewModels;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return reviewModels.size();
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder {
        TextView authorName,movieContent;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);

            authorName = itemView.findViewById(R.id.movie_author);
            movieContent = itemView.findViewById(R.id.movie_content);

        }
    }
}
