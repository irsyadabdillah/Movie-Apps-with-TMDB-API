package com.irzstudio.movieapps.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irzstudio.movieapps.data.Repository.Repository
import com.irzstudio.movieapps.model.cast.Cast
import com.irzstudio.movieapps.model.datailfilm.DetailResponse
import com.irzstudio.movieapps.model.favorite.FavoriteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val _detailResponse = MutableLiveData<DetailResponse>()
    val detailResponse: LiveData<DetailResponse> = _detailResponse

    private val _castResponseList = MutableLiveData<ArrayList<Cast>>()
    val castResponseList: LiveData<ArrayList<Cast>> = _castResponseList

    private val _isfavorited = MutableLiveData<Boolean>()
    val isFavorited: LiveData<Boolean> = _isfavorited

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage


    fun requestDetailMovie(id: Int) = viewModelScope.launch {
        repository.getDetailMovie(id).let {
            if (it.isSuccessful) {
                _detailResponse.postValue(it.body())
            } else {
                _errorMessage.postValue(it.message())
            }
        }

    }

    fun requestCast(id: Int) = viewModelScope.launch {
        repository.getCast(id).let {
            if (it.isSuccessful) {
                _castResponseList.postValue(it.body()?.cast)
            } else {
                _errorMessage.postValue(it.message())
            }
        }
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

