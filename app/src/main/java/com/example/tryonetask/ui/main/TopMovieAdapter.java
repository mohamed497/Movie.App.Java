package com.example.tryonetask.ui.main;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tryonetask.Detalis.SingleMovieActivity;
import com.example.tryonetask.R;
import com.example.tryonetask.pojo.TopMovieModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TopMovieAdapter extends RecyclerView.Adapter<TopMovieAdapter.MovieViewHolder> {
    private Context context;
    private List<TopMovieModel> movieModels;


    private boolean isLoadingAdded = false;
    SingleMovieActivity singleMovieActivity = new SingleMovieActivity();

    private static final String BASE_URL_IMG = "http://image.tmdb.org/t/p/w500";

    public TopMovieAdapter() {
    }

//    public MovieAdapter( Context context,List<MovieModel> movieModels) {
//        this.context = context;
//        this.movieModels = movieModels;
//    }

    public TopMovieAdapter(Context context) {
        this.context = context;
        movieModels = new ArrayList<>();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        context = parent.getContext();
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        TopMovieModel movie = movieModels.get(position);

        holder.title.setText(movie.getTitle());
        Glide.with(context).load(BASE_URL_IMG+movie.getPoster_path()).into(holder.img);

    }

    public void setList(List<TopMovieModel> movieModels) {
        this.movieModels = movieModels;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        Log.d("zxc", " " + movieModels.size());
        return movieModels.size();
    }


    public List<TopMovieModel> getMovies() {
        return movieModels;
    }


    public void add(TopMovieModel r) {
        movieModels.add(r);
        notifyItemInserted(movieModels.size() - 1);
    }

    public void addAll(List<TopMovieModel> movieModel) {
        for (TopMovieModel movie : movieModel) {
            add(movie);
        }
    }

    public void remove(TopMovieModel r) {
        int position = movieModels.indexOf(r);
        if (position > -1) {
            movieModels.remove(position);
            notifyItemRemoved(position);
        }
    }




    public TopMovieModel getItem(int position) {
        return movieModels.get(position);
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
