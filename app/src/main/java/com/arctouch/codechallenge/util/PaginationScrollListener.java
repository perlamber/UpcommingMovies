package com.arctouch.codechallenge.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class PaginationScrollListener<DefaultAdapter> extends RecyclerView.OnScrollListener {


    protected static final int PAGE_START = 1;
    protected boolean isLoading = false;
    protected int currentPage = PAGE_START;

    protected LinearLayoutManager layoutManager;
    protected DefaultAdapter adapter;

    public PaginationScrollListener(LinearLayoutManager layoutManager, DefaultAdapter adapter) {
        this.layoutManager = layoutManager;
        this.adapter = adapter;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

        if (!isLoading()) {
            if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                    && firstVisibleItemPosition >= 0) {
                loadMoreItems();
            }
        }
    }

    protected abstract void loadMoreItems();

    public boolean isLoading() {
        return isLoading;
    }
}
