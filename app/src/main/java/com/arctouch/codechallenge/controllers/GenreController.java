package com.arctouch.codechallenge.controllers;

import com.arctouch.codechallenge.api.ApiConfig;
import com.arctouch.codechallenge.api.GenreService;
import com.arctouch.codechallenge.api.ApiRepository;
import com.arctouch.codechallenge.data.Cache;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class GenreController {

    private static GenreService service;

    public static void cacheGenres(@NonNull Action action)
    {
        getService().genres(ApiConfig.API_KEY, ApiConfig.DEFAULT_LANGUAGE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    Cache.setGenres(response.genres);
                }, response -> {
                    Cache.getGenres().clear();
                }, action);
    }

    public static void cacheGenres()
    {
        cacheGenres(() -> {});
    }

    private static GenreService getService()
    {
        if(service == null)
        {
            service = ApiRepository.getInstance().create(GenreService.class);
        }
        return service;
    }
}
