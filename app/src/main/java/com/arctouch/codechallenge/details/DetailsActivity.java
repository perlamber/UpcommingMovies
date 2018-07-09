package com.arctouch.codechallenge.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.util.ImageUtils;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Movie movie = (Movie) this.getIntent().getExtras().getSerializable("MOVIE");

        ImageView poster = (ImageView)findViewById(R.id.movie_poster);
        TextView title = (TextView)findViewById(R.id.movie_title);
        TextView genres = (TextView)findViewById(R.id.movie_genres);
        TextView date = (TextView)findViewById(R.id.movie_date);
        TextView overview = (TextView)findViewById(R.id.movie_overview);
        ImageView backdrop = (ImageView)findViewById(R.id.movie_backdrop);

        ImageUtils.loadPosterImageIntoView(movie.posterPath, poster, this);
        ImageUtils.loadBackdropImageIntoView(movie.backdropPath, backdrop, this);

        title.setText(movie.title);
        genres.setText(TextUtils.join(", ", movie.genres));
        date.setText(movie.releaseDate);
        overview.setText(movie.overview);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
