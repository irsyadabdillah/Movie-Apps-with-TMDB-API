package com.irzstudio.movieapps.remote;

import com.irzstudio.movieapps.util.Constant
import com.irzstudio.movieapps.model.cast.CastResponse
import com.irzstudio.movieapps.model.datailfilm.DetailResponse
import com.irzstudio.movieapps.model.discover.DiscoverResponse
import com.irzstudio.movieapps.model.trending.TrendingResponse
import com.irzstudio.movieapps.model.upcoming.UpcomingResponse
import retrofit2.Call
import retrofit2.http.GET;
import retrofit2.http.Path
import retrofit2.http.Query;

interface Api {
    @GET("trending/all/day")
    fun getTrending(
        @Query("api_key") apiKey: String = Constant.API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ):Call<TrendingResponse>

    @GET("movie/upcoming")
    fun getUpcoming(
        @Query("api_key") apiKey: String = Constant.API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ):Call<UpcomingResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movie: Int,
        @Query("api_key") apiKey: String = Constant.API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ):Call<DetailResponse>

    @GET("discover/movie")
    fun getDiscover(
        @Query("api_key") apiKey: String = Constant.API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ):Call<DiscoverResponse>

    @GET("movie/{cast_id}/credits")
    fun getCast(
        @Path("cast_id") cast: Int,
        @Query("api_key") apiKey: String = Constant.API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ):Call<CastResponse>
}
