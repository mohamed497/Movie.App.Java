package com.example.tryonetask.tryPaging;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tryonetask.Detalis.SingleMovieActivity;
import com.example.tryonetask.Detalis.TryReviewActivity;
import com.example.tryonetask.R;
import com.example.tryonetask.pojo.MovieModel;
import com.example.tryonetask.pojo.TopMovieModel;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Alaa Moaataz on 2020-01-27.
 */
public class TopItemAdapter extends PagedListAdapter<TopMovieModel, TopItemAdapter.ItemViewHolder> {


    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";


    Context mCtx;
    private static final String BASE_URL_IMG = "http://image.tmdb.org/t/p/w500";
    private List<TopMovieModel> movieList;


    private final TopTextClickListener listener;


    public TopItemAdapter(Context mCtx, TopTextClickListener listener){
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.movie_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        TopMovieModel movie = getItem(position);

        if(movie != null){

            holder.title.setText(movie.getTitle());
            Glide.with(mCtx).load(BASE_URL_IMG+movie.getPoster_path()).into(holder.img);

            boolean check = false;
            if (holder.newTitle != null){
                check = holder.newTitle.getVisibility() == View.VISIBLE;
            }

            holder.materialFavoriteButtonNice.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    if(favorite){
                        if(checkFavoriteItem(movie)){
                            Toast.makeText(mCtx, "Cant Add Movie Twice ! ! ", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Snackbar.make(buttonView, "Added to Favorite",
                                    Snackbar.LENGTH_SHORT).show();
                            addFavorite(mCtx,movie);
                            Intent intent = new Intent(mCtx, TryReviewActivity.class);
                            intent.putExtra("movie",movie);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mCtx.startActivity(intent);
                        }
                    }else{
                        removeFavorite(mCtx, movie);
                        Snackbar.make(buttonView, "Removed SUCC !!!", Snackbar.LENGTH_SHORT).show();
                    }
                }
            });


            if(check){
                holder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(listener != null){
                            listener.onTopTextClick(movie);
                        }
                    }
                });

                holder.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("movieee","MOVIE == :"+movie);
                        if(checkFavoriteItem(movie)){
                            Toast.makeText(mCtx, "Cant Add Movie Twice ! ! ", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            addFavorite(mCtx,movie);
                            Intent intent = new Intent(mCtx, TryReviewActivity.class);
                            intent.putExtra("movie",movie);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mCtx.startActivity(intent);
                        }

                    }
                });



            }
            else{

                holder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(listener != null){
                            listener.onTopTextClick(movie);
                        }
                    }
                });

//                holder.img.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(mCtx, SingleMovieActivity.class);
////                intent.putExtra("MOVIE_ID",movie.id);
//                        intent.putExtra("movie",movie);
////                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                intent.putExtra("MOVIE_OVERVIEW",movie.getOverview());
////                intent.putExtra("MOVIE_TITLE",movie.getTitle());
////                intent.putExtra("MOVIE_POSTER",movie.getPoster_path());
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        mCtx.startActivity(intent);
//                    }
//                });
            }

//            holder.img.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    boolean check = false;
//                    if (holder.newTitle != null){
//                        check = holder.newTitle.getVisibility() == View.VISIBLE;
//                    }
//                    if(check){
//                        if(listener != null){
//                            listener.onTextClick(movie);
//                        }
//
//
////                        Intent intent = new Intent(mCtx, MainActivity.class);
////                        intent.putExtra("movie",movie);
////                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                        mCtx.startActivity(intent);
//
////                        Bundle bundle = new Bundle();
////                        bundle.putString("MOVIE_TITLE", movie.getTitle());
////                        bundle.putString("MOVIE_OVERVIEW", movie.getOverview());
////                        bundle.putString("MOVIE_POSTER", movie.getPoster_path());
////                        bundle.putInt("MOVIE_ID",movie.id);
////                        bundle.putParcelable("MOVIE",movie);
////                        DetailsFragment myFrag = new DetailsFragment();
////                        myFrag.setArguments(bundle);
////                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
////                        DetailsFragment myFragment = new DetailsFragment();
////                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.zxc , myFragment).addToBackStack(null).commit();
//                    }
//                    else
//                    {
//                        Intent intent = new Intent(mCtx, SingleMovieActivity.class);
////                intent.putExtra("MOVIE_ID",movie.id);
//                        intent.putExtra("movie",movie);
////                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                intent.putExtra("MOVIE_OVERVIEW",movie.getOverview());
////                intent.putExtra("MOVIE_TITLE",movie.getTitle());
////                intent.putExtra("MOVIE_POSTER",movie.getPoster_path());
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        mCtx.startActivity(intent);
//                    }
//
//
//                }
//            });


        }else{
            Toast.makeText(mCtx, "Item is null", Toast.LENGTH_LONG).show();
        }



    }

    private static DiffUtil.ItemCallback<TopMovieModel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<TopMovieModel>() {
                @Override
                public boolean areItemsTheSame(@NonNull TopMovieModel oldItem, @NonNull TopMovieModel newItem) {
                    return oldItem.getTitle() == newItem.getTitle();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull TopMovieModel oldItem, @NonNull TopMovieModel newItem) {
                    return oldItem.equals(newItem);
                }
            };



    public class ItemViewHolder extends RecyclerView.ViewHolder {

        MaterialFavoriteButton materialFavoriteButtonNice;
        TextView title,newTitle;
        ImageView img;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            newTitle = itemView.findViewById(R.id.title_title);
            title = itemView.findViewById(R.id.get_title);
            img = itemView.findViewById(R.id.movie_img);
            materialFavoriteButtonNice = itemView.findViewById(R.id.favorite_button);

        }
    }




    public void saveFavorites(Context context, List<TopMovieModel> favorites) {
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

    public void addFavorite(Context context, TopMovieModel movie) {
        List<TopMovieModel> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<TopMovieModel>();
        favorites.add(movie);
        saveFavorites(context, favorites);
    }
    public void removeFavorite(Context context, TopMovieModel movie) {
        ArrayList<TopMovieModel> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(movie);
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<TopMovieModel> getFavorites(Context context) {
        SharedPreferences settings;
        List<TopMovieModel> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();

            TopMovieModel[] favoriteItems = gson.fromJson(jsonFavorites,
                    TopMovieModel[].class);
            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<TopMovieModel>(favorites);

        } else
            return null;

        return (ArrayList<TopMovieModel>) favorites;

    }

    public boolean checkFavoriteItem(TopMovieModel checkProduct) {
        boolean check = false;
        List<TopMovieModel> favorites = getFavorites(mCtx);
        if (favorites != null) {
            for (TopMovieModel movie : favorites) {
                if (movie.equals(checkProduct)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

}
