package com.zed;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonApi {

    @GET("/3/movie/upcoming")
    Call<tmdbNowPlaying> getUpcoming(@Query("api_key") String apiKey,
                                     @Query("page")int page);
    @GET("/3/movie/now_playing")
    Call<tmdbNowPlaying> getNowPlaying(@Query("api_key") String apiKey,
                             @Query("page")int page);

    @GET("/3/movie/{movie_id}")
    Response<tmdb> getMovie(@Path("movie_id") String movieId, @Query("api_key") String apiKey,
                            @Query("page")int page);
}
