package com.arctouch.codechallenge.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.arctouch.codechallenge.R;
import com.arctouch.codechallenge.controllers.MovieController;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        this.recyclerView = findViewById(R.id.recyclerView);
        this.progressBar = findViewById(R.id.progressBar);

        HomeAdapter hAdapter = new HomeAdapter();
        recyclerView.setAdapter(hAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addOnScrollListener(new HomeAdapterScrollListener((LinearLayoutManager)recyclerView.getLayoutManager(), progressBar, hAdapter));
        MovieController.chargeGenreAndMovies(hAdapter);

        progressBar.setVisibility(View.GONE);
    }
}
