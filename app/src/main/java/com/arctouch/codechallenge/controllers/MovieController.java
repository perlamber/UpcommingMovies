package com.arctouch.codechallenge.controllers;


import com.arctouch.codechallenge.api.ApiConfig;
import com.arctouch.codechallenge.api.MovieService;
import com.arctouch.codechallenge.api.ApiRepository;
import com.arctouch.codechallenge.data.Cache;
import com.arctouch.codechallenge.model.Genre;
import com.arctouch.codechallenge.model.Movie;
import com.arctouch.codechallenge.model.UpcomingMoviesResponse;
import com.arctouch.codechallenge.util.DefaultAdapter;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class MovieController {

    private static MovieService service;

    public static void chargeGenreAndMovies(@NonNull DefaultAdapter<Movie> adpter) {
        if(Cache.getGenres().isEmpty())
        {
            GenreController.cacheGenres(() -> chargeMovies(adpter, 1L));
        }
        else
        {
            chargeMovies(adpter, 1L);
        }
    }

    public static void chargeMovies(@NonNull DefaultAdapter<Movie> adpter, long page,@NonNull Action onComplete)
    {
        getUpcomingMoviesObservable(page).subscribe(response -> {
            for (Movie movie : response.results) {
                movie.genres = new ArrayList<>();
                for (Genre genre : Cache.getGenres()) {
                    if (movie.genreIds.contains(genre.id)) {
                        movie.genres.add(genre);
                    }
                }
            }
            adpter.addData(response.results);
        }, response -> response.printStackTrace(), onComplete);
    }

    public static void chargeMovies(@NonNull DefaultAdapter<Movie> adpter, long page)
    {
        chargeMovies(adpter, page, () -> {});
    }

    private static Observable<UpcomingMoviesResponse> getUpcomingMoviesObservable(long page)
    {
        return getService().upcomingMovies(ApiConfig.API_KEY, ApiConfig.DEFAULT_LANGUAGE, page, ApiConfig.DEFAULT_REGION)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private static MovieService getService()
    {
        if(service == null)
        {
            service = ApiRepository.getInstance().create(MovieService.class);
        }
        return service;
    }
}
