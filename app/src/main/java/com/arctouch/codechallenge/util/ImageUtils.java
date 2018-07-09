package com.arctouch.codechallenge.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.arctouch.codechallenge.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import io.reactivex.annotations.NonNull;

public class ImageUtils {
    private final static MovieImageUrlBuilder movieImageUrlBuilder = new MovieImageUrlBuilder();

    public static void loadPosterImageIntoView(@NonNull String url, @NonNull ImageView view, @NonNull Context context)
    {
        if (TextUtils.isEmpty(url) == false) {
            Glide.with(context)
                    .load(movieImageUrlBuilder.buildPosterUrl(url))
                    .apply(new RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                    .into(view);
        }
    }

    public static void loadBackdropImageIntoView(@NonNull String url, @NonNull ImageView view, @NonNull Context context)
    {
        if (TextUtils.isEmpty(url) == false) {
            Glide.with(context)
                    .load(movieImageUrlBuilder.buildBackdropUrl(url))
                    .apply(new RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                    .into(view);
        }
    }
}
