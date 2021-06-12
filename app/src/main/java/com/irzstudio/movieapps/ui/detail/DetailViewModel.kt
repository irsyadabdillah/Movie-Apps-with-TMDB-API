package com.irzstudio.movieapps.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irzstudio.movieapps.data.Repository
import com.irzstudio.movieapps.model.cast.Cast
import com.irzstudio.movieapps.model.cast.CastResponse
import com.irzstudio.movieapps.model.datailfilm.DetailResponse
import com.irzstudio.movieapps.model.favorite.FavoriteDatabase
import com.irzstudio.movieapps.model.favorite.FavoriteEntity
import com.irzstudio.movieapps.remote.RetrofitClient
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel( val repository: Repository) : ViewModel() {

    private val _detailResponse = MutableLiveData<DetailResponse>()
    val detailResponse: LiveData<DetailResponse> = _detailResponse

    private val _castResponseList = MutableLiveData<ArrayList<Cast>>()
    val castResponseList: LiveData<ArrayList<Cast>> = _castResponseList

    private val _isfavorited = MutableLiveData<Boolean>()
    val isFavorited: LiveData<Boolean> = _isfavorited

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage


    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    fun requestDetailMovie(id: Int) {
        val detailMovieDisposable = repository.getDetailMovie(id)
            .doOnSubscribe { }
            .doFinally { }
            .subscribe({ _detailResponse.postValue(it) }, { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(detailMovieDisposable)
    }

    fun requestCast(id: Int) {
        val castDisposable = repository.getCast(id)
            .doOnSubscribe { }
            .doFinally { }
            .subscribe({ _castResponseList.postValue(it.cast) }, { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(castDisposable)
    }

    fun saveMovie() {
        val detail = _detailResponse.value!!
        val favorite = FavoriteEntity(
            id = detail.id,
            rating = detail.rating,
            backdropPath = detail.backdropPath,
            posterPath = detail.posterPath,
            originalTitle = detail.originalTitle,
            releaseDate = detail.releaseDate
        )
        repository.saveMovie(favorite)
    }

    fun removeMovie() {
        val detail = _detailResponse.value!!
        repository.removeMovie(detail.id)
    }

    fun checkFavMovie() {
        val detail = _detailResponse.value!!
        _isfavorited.postValue(repository.checkMovie(detail.id))
    }
}