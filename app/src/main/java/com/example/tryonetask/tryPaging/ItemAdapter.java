package com.example.tryonetask.tryPaging;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tryonetask.R;
import com.example.tryonetask.Detalis.SingleMovieActivity;
import com.example.tryonetask.pojo.MovieModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Alaa Moaataz on 2020-01-27.
 */
public class ItemAdapter extends PagedListAdapter<MovieModel , ItemAdapter.ItemViewHolder> {



    Context mCtx;
    private static final String BASE_URL_IMG = "http://image.tmdb.org/t/p/w500";
    private List<MovieModel> movieList;


    private final OnTextClickListener listener;


    public ItemAdapter(Context mCtx, OnTextClickListener listener){
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

        MovieModel movie = getItem(position);


        if(movie != null){

            holder.title.setText(movie.getTitle());
            Glide.with(mCtx).load(BASE_URL_IMG+movie.getPoster_path()).into(holder.img);


            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean check = false;
                    if (holder.newTitle != null){
                        check = holder.newTitle.getVisibility() == View.VISIBLE;
                    }
                    if(check){
                        if(listener != null){
                            listener.onTextClick(movie);
                        }


//                        Intent intent = new Intent(mCtx, MainActivity.class);
//                        intent.putExtra("movie",movie);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        mCtx.startActivity(intent);

//                        Bundle bundle = new Bundle();
//                        bundle.putString("MOVIE_TITLE", movie.getTitle());
//                        bundle.putString("MOVIE_OVERVIEW", movie.getOverview());
//                        bundle.putString("MOVIE_POSTER", movie.getPoster_path());
//                        bundle.putInt("MOVIE_ID",movie.id);
//                        bundle.putParcelable("MOVIE",movie);
//                        DetailsFragment myFrag = new DetailsFragment();
//                        myFrag.setArguments(bundle);
//                        AppCompatActivity activity = (AppCompatActivity) v.getContext();
//                        DetailsFragment myFragment = new DetailsFragment();
//                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.zxc , myFragment).addToBackStack(null).commit();
                    }
                    else
                    {
                        Intent intent = new Intent(mCtx, SingleMovieActivity.class);
//                intent.putExtra("MOVIE_ID",movie.id);
                        intent.putExtra("movie",movie);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("MOVIE_OVERVIEW",movie.getOverview());
//                intent.putExtra("MOVIE_TITLE",movie.getTitle());
//                intent.putExtra("MOVIE_POSTER",movie.getPoster_path());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mCtx.startActivity(intent);
                    }


                }
            });


        }else{
            Toast.makeText(mCtx, "Item is null", Toast.LENGTH_LONG).show();
        }



    }

    private static DiffUtil.ItemCallback<MovieModel> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<MovieModel>() {
                @Override
                public boolean areItemsTheSame(@NonNull MovieModel oldItem, @NonNull MovieModel newItem) {
                    return oldItem.getTitle() == newItem.getTitle();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(@NonNull MovieModel oldItem, @NonNull MovieModel newItem) {
                    return oldItem.equals(newItem);
                }
            };



    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView title,newTitle;
        ImageView img;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            newTitle = itemView.findViewById(R.id.title_title);
            title = itemView.findViewById(R.id.get_title);
            img = itemView.findViewById(R.id.movie_img);

        }
    }
}
