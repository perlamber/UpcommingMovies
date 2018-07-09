package com.arctouch.codechallenge.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.util.DefaultAdapter;
import com.arctouch.codechallenge.util.MovieImageUrlBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> implements DefaultAdapter<Movie> {

    private List<Movie> movies;
    private boolean isLoading = false;

    public HomeAdapter() {
        this.movies = new ArrayList<Movie>();
    }


    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    public void addData(List<Movie> data){
        this.movies.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public void addLoadingFooter() {
        isLoading = true;
    }

    @Override
    public void removeLoadingFooter() {
        isLoading = false;
    }

    public Movie getItem(int position) {
        return movies.get(position);
    }
}
