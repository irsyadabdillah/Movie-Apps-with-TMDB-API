package com.irzstudio.movieapps.screen.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irzstudio.movieapps.model.cast.Cast
import com.irzstudio.movieapps.model.cast.CastResponse
import com.irzstudio.movieapps.model.datailfilm.DetailResponse
import com.irzstudio.movieapps.model.favorite.FavoriteDatabase
import com.irzstudio.movieapps.model.favorite.FavoriteEntity
import com.irzstudio.movieapps.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val _detailResponse = MutableLiveData<DetailResponse>()
    val detailResponse: LiveData<DetailResponse> = _detailResponse

    private val _castResponseList = MutableLiveData<ArrayList<Cast>>()
    val castResponseList: LiveData<ArrayList<Cast>> = _castResponseList

    private val _isfavorited = MutableLiveData<Boolean>()
    val isFavorited: LiveData<Boolean> = _isfavorited

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private var database: FavoriteDatabase? = null

    init {
        database = FavoriteDatabase.getInstance()
    }

    fun requestDetailMovie(id: Int) {
        RetrofitClient.instance.getDetailMovie(id).enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                response.body()?.let {
                    _detailResponse.postValue(it)
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                t.printStackTrace()
                _errorMessage.postValue(t.localizedMessage)
            }
        })
    }

    fun requestCast(id: Int) {
        RetrofitClient.instance.getCast(id).enqueue(object : Callback<CastResponse> {
            override fun onResponse(call: Call<CastResponse>, response: Response<CastResponse>) {
                response.body()?.let {
                    _castResponseList.postValue(it.cast)
                }
            }

            override fun onFailure(call: Call<CastResponse>, t: Throwable) {
                t.printStackTrace()
                _errorMessage.postValue(t.localizedMessage)
            }
        })
    }

    fun saveMovie() { //mengambil semua object (yg diperlukan cuma id nya)

        val detail = _detailResponse.value!!

        val favorite = FavoriteEntity(
            id = detail.id,
            backdropPath = detail.backdropPath,
            posterPath = detail.posterPath,
            originalTitle = detail.originalTitle,
            releaseDate = detail.releaseDate
        )
        database?.favoriteDao()?.insert(favorite)
    }


    fun removeMovie() { //dengan cara membuat query untuk mengambil id nya
        val detail = _detailResponse.value!!
        database?.favoriteDao()?.deleteById(detail.id)
    }

    fun checkFavMovie() {
        val detail = _detailResponse.value!!
        val isFavorited = database?.favoriteDao()?.getMovieById(detail.id)?.size != 0
        _isfavorited.postValue(isFavorited)

    }


}