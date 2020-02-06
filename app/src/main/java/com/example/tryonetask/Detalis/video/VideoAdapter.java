package com.example.tryonetask.Detalis.video;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tryonetask.Detalis.SingleMovieActivity;
import com.example.tryonetask.R;
import com.example.tryonetask.pojo.videos_data.VideoModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Alaa Moaataz on 2020-02-04.
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private Context context;
    private List<VideoModel> videoModels;


    public VideoAdapter() {
    }

    public VideoAdapter(Context context) {
        this.context = context;
        videoModels = new ArrayList<>();
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.video_item, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {

        VideoModel videoModel = videoModels.get(position);

        holder.movieTrailer.setText("MOVIE TraiLeR");


    }


    public void setList(List<VideoModel> videoModels){
        this.videoModels = videoModels;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return videoModels.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView movieTrailer;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTrailer = itemView.findViewById(R.id.video_trailer);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION){
                        VideoModel clickedDataItem = videoModels.get(pos);
                        String videoId = videoModels.get(pos).getKey();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+videoId));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("VIDEO_ID", videoId);
                        context.startActivity(intent);

                    }
                }
            });

        }
    }
}
