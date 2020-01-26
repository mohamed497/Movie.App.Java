package com.example.tryonetask.ui.main;

import android.content.Context;
import android.graphics.Movie;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tryonetask.R;
import com.example.tryonetask.pojo.MovieModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    private List<MovieModel> movieModels;

    private static final String BASE_URL_IMG = "http://image.tmdb.org/t/p/w500";

    public MovieAdapter() {
    }

//    public MovieAdapter( Context context,List<MovieModel> movieModels) {
//        this.context = context;
//        this.movieModels = movieModels;
//    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        context = parent.getContext();
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        MovieModel movie = movieModels.get(position);

        holder.title.setText(movie.getTitle());
        Glide.with(context).load(BASE_URL_IMG+movie.getPoster_path()).into(holder.img);

    }

    public void setList(List<MovieModel> movieModels) {
        this.movieModels = movieModels;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        Log.d("zxc", " " + movieModels.size());
        return movieModels.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.get_title);
            img = itemView.findViewById(R.id.movie_img);
        }
    }
}
