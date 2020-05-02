package com.zed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private List<tmdbNowPlaying.Result> dataList;
    private Context context;
    OnDataItemClickListener ondataItemClickListener;

    CustomAdapter(Context context, List<tmdbNowPlaying.Result> dataList, OnDataItemClickListener onDataItemClickListener){
        this.context = context;
        this.dataList = dataList;
        this.ondataItemClickListener = onDataItemClickListener;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final View mView;
        TextView txtTitle, year, rating;
        private ImageView poster;
        OnDataItemClickListener onDataItemClickListener;
        public CustomViewHolder(@NonNull View itemView, OnDataItemClickListener onDataItemClickListener) {
            super(itemView);
            mView = itemView;
            txtTitle = mView.findViewById(R.id.movie_name);
            poster = mView.findViewById(R.id.poster);
            year = mView.findViewById(R.id.year);
            rating = mView.findViewById(R.id.rating);
            this.onDataItemClickListener = onDataItemClickListener;
            mView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onDataItemClickListener.onItemClick(getAdapterPosition());
        }
    }
    interface OnDataItemClickListener{
        void onItemClick(int position);
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.movie_list_item, parent, false);
        return new CustomViewHolder(view,ondataItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        String imageUrl = "https://image.tmdb.org/t/p/original"+ dataList.get(position).getPosterPath();
        holder.rating.setText(dataList.get(position).getVoteAverage().toString());
        holder.year.setText(dataList.get(position).getReleaseDate());
        holder.txtTitle.setText( dataList.get(position).getTitle());

        Glide.with(holder.mView)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


}
