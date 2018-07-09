package com.arctouch.codechallenge.home;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;

import com.arctouch.codechallenge.controllers.MovieController;
import com.arctouch.codechallenge.util.PaginationScrollListener;

public class HomeAdapterScrollListener extends PaginationScrollListener<HomeAdapter> {

    private ProgressBar progressBar;

    public HomeAdapterScrollListener(LinearLayoutManager layoutManager, ProgressBar progressBar, HomeAdapter homeAdapter) {
        super(layoutManager,homeAdapter);
        this.progressBar = progressBar;
    }

    @Override
    protected void loadMoreItems() {
        isLoading = true;
        currentPage += 1;
        progressBar.setVisibility(View.VISIBLE);
        MovieController.chargeMovies(adapter, currentPage, () -> loadNextPage());
    }

    private void loadNextPage() {
        adapter.removeLoadingFooter();
        isLoading = false;
        adapter.addLoadingFooter();
        progressBar.setVisibility(View.GONE);
    }
}
