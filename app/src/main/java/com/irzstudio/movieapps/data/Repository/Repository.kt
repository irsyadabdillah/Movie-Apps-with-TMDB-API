package com.irzstudio.movieapps.data.Repository

import com.irzstudio.movieapps.data.Local.AppDatabase
import com.irzstudio.movieapps.data.Network.ApiService
import com.irzstudio.movieapps.model.cast.CastResponse
import com.irzstudio.movieapps.model.datailfilm.DetailResponse
import com.irzstudio.movieapps.model.discover.DiscoverResponse
import com.irzstudio.movieapps.model.favorite.FavoriteEntity
import com.irzstudio.movieapps.model.search.SearchResponse
import com.irzstudio.movieapps.model.trending.TrendingResponse
import com.irzstudio.movieapps.model.upcoming.UpcomingResponse
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(val apiService: ApiService, val appDatabase: AppDatabase) {

    suspend fun getDetailMovie(id: Int): Response<DetailResponse> {
        return apiService.getDetailMovie(id)
    }

    suspend fun getCast(id: Int): Response<CastResponse> {
        return apiService.getCast(id)
    }

    suspend fun getDiscover(): Response<DiscoverResponse> {
        return apiService.getDiscover()
    }

    suspend fun getTrending(): Response<TrendingResponse> {
        return apiService.getTrending()
    }

    suspend fun getUpcoming(): Response<UpcomingResponse> {
        return apiService.getUpcoming()
    }

    suspend fun getMovieQuery(query: String?): Response<SearchResponse> {
        return apiService.getSearchMovie(query.orEmpty())
    }

    fun saveMovie(favoriteEntity: FavoriteEntity) {
        appDatabase?.favoriteDao()?.insert(favoriteEntity)
    }

    fun removeMovie(id: Int) {
        appDatabase?.favoriteDao()?.deleteById(id)
    }

    fun checkMovie(id: Int): Boolean {
        val isFavorited = appDatabase?.favoriteDao()?.getMovieById(id)?.size != 0
        return isFavorited
    }

    fun getAllDb(): ArrayList<FavoriteEntity> {
        val dataFromBd = appDatabase?.favoriteDao()?.getAll()
        return dataFromBd as ArrayList<FavoriteEntity>
    }

}