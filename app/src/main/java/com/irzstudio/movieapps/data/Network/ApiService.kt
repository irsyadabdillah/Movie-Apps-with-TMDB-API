package com.irzstudio.movieapps.data.Network;

import com.irzstudio.movieapps.model.cast.CastResponse
import com.irzstudio.movieapps.model.datailfilm.DetailResponse
import com.irzstudio.movieapps.model.discover.DiscoverResponse
import com.irzstudio.movieapps.model.search.SearchResponse
import com.irzstudio.movieapps.model.trending.TrendingResponse
import com.irzstudio.movieapps.model.upcoming.UpcomingResponse
import com.irzstudio.movieapps.util.Constant
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("trending/all/day")
    suspend fun getTrending(
        @Query("api_key") apiKey: String = Constant.API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Response<TrendingResponse>

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key") apiKey: String = Constant.API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Response<UpcomingResponse>

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movie: Int,
        @Query("api_key") apiKey: String = Constant.API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Response<DetailResponse>

    @GET("discover/movie")
    suspend fun getDiscover(
        @Query("api_key") apiKey: String = Constant.API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Response<DiscoverResponse>

    @GET("movie/{cast_id}/credits")
    suspend fun getCast(
        @Path("cast_id") cast: Int,
        @Query("api_key") apiKey: String = Constant.API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE
    ): Response<CastResponse>

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query("query") query: String,
        @Query("api_key") apiKey: String = Constant.API_KEY,
        @Query("language") lang: String = Constant.LANGUAGE,
        @Query("page") page: String = Constant.PAGE,
        @Query("include_adult") adult: String = Constant.INCLUDE_ADULT
    ): Response<SearchResponse>
}
