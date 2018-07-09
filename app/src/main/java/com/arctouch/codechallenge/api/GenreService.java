package com.arctouch.codechallenge.api;

import com.arctouch.codechallenge.model.GenreResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GenreService {

    @GET("genre/movie/list")
    Observable<GenreResponse> genres(
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

}
