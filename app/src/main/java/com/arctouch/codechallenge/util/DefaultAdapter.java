package com.arctouch.codechallenge.util;

import java.util.List;

public interface DefaultAdapter<T> {
    public void addData(List<T> items);
    public void removeLoadingFooter();
    public void addLoadingFooter();
}
