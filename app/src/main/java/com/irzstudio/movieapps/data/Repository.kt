package com.irzstudio.movieapps.data

import com.irzstudio.movieapps.model.cast.CastResponse
import com.irzstudio.movieapps.model.datailfilm.DetailResponse
import com.irzstudio.movieapps.model.discover.DiscoverResponse
import com.irzstudio.movieapps.model.favorite.FavoriteDatabase
import com.irzstudio.movieapps.model.favorite.FavoriteEntity
import com.irzstudio.movieapps.model.search.SearchResponse
import com.irzstudio.movieapps.model.trending.TrendingResponse
import com.irzstudio.movieapps.model.upcoming.UpcomingResponse
import com.irzstudio.movieapps.remote.RetrofitClient
import io.reactivex.Observable

class Repository {
    private var favoriteDatabase: FavoriteDatabase? = null
    init {
        favoriteDatabase = FavoriteDatabase.getInstance()
    }


    fun getDetailMovie(id: Int): Observable<DetailResponse> {
        return RetrofitClient.instance.getDetailMovie(id)
    }

    fun getCast(id: Int): Observable<CastResponse> {
        return RetrofitClient.instance.getCast(id)
    }

    fun getDiscover(): Observable<DiscoverResponse> {
        return RetrofitClient.instance.getDiscover()
    }

    fun getTrending(): Observable<TrendingResponse> {
        return RetrofitClient.instance.getTrending()
    }

    fun getUpcoming(): Observable<UpcomingResponse> {
        return RetrofitClient.instance.getUpcoming()
    }

    fun getMovieQuery(query: String?): Observable<SearchResponse> {
        return RetrofitClient.instance.getSearchMovie(query.orEmpty())
    }

    fun saveMovie(favoriteEntity: FavoriteEntity) {
        favoriteDatabase?.favoriteDao()?.insert(favoriteEntity)
    }

    fun removeMovie(id: Int) {
        favoriteDatabase?.favoriteDao()?.deleteById(id)
    }

    fun checkMovie(id: Int): Boolean {
        val isFavorited = favoriteDatabase?.favoriteDao()?.getMovieById(id)?.size != 0
        return isFavorited
    }

    fun getAllDb(): ArrayList<FavoriteEntity> {
        val dataFromBd = favoriteDatabase?.favoriteDao()?.getAll().orEmpty()
        return dataFromBd as ArrayList<FavoriteEntity>
    }

}