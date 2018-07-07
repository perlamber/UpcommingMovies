package com.arctouch.codechallenge.api.service;


import com.arctouch.codechallenge.api.TmdbApi;
import com.arctouch.codechallenge.data.Cache;
import com.arctouch.codechallenge.model.Genre;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.model.UpcomingMoviesResponse;
import com.arctouch.codechallenge.util.DefaultAdapter;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class MovieApiService {

    public static void chargeGenreAndMovies(DefaultAdapter<Movie> adpter) {
        if(Cache.getGenres().isEmpty())
        {
            GenreApiService.cacheGenres(() -> chargeMovies(adpter));
        }
        else
        {
            chargeMovies(adpter);
        }
    }

    private static void chargeMovies(DefaultAdapter<Movie> adpter)
    {
        getUpcomingMoviesObservable().subscribe(response -> {
            for (Movie movie : response.results) {
                movie.genres = new ArrayList<>();
                for (Genre genre : Cache.getGenres()) {
                    if (movie.genreIds.contains(genre.id)) {
                        movie.genres.add(genre);
                    }
                }
            }
            adpter.setData(response.results);
        });
    }

    private static Observable<UpcomingMoviesResponse> getUpcomingMoviesObservable()
    {
        return ApiService.getApi().upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, 1L, TmdbApi.DEFAULT_REGION)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
