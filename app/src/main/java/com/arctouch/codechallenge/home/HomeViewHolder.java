package com.arctouch.codechallenge.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.details.DetailsActivity;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.util.ImageUtils;

public class HomeViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        private Movie currMovie;

        private final TextView titleTextView;
        private final TextView genresTextView;
        private final TextView releaseDateTextView;
        private final ImageView posterImageView;

        public HomeViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            genresTextView = itemView.findViewById(R.id.genresTextView);
            releaseDateTextView = itemView.findViewById(R.id.releaseDateTextView);
            posterImageView = itemView.findViewById(R.id.posterImageView);
            itemView.setOnClickListener(this);
        }

        public void bind(Movie movie) {
            currMovie = movie;
            //Fill the screen components
            titleTextView.setText(movie.title);
            genresTextView.setText(TextUtils.join(", ", movie.genres));
            releaseDateTextView.setText(movie.releaseDate);
            ImageUtils.loadPosterImageIntoView(movie.posterPath, posterImageView, itemView.getContext());
        }

    @Override
    public void onClick(View view) {
        Bundle extras = new Bundle();
        extras.putSerializable("MOVIE", currMovie);
        Intent intent = new Intent(view.getContext(), DetailsActivity.class);
        intent.putExtras(extras);
        view.getContext().startActivity(intent);
    }
}
