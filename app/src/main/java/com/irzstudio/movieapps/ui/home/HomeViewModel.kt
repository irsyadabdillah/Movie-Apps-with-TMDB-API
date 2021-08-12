package com.irzstudio.movieapps.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irzstudio.movieapps.data.Repository.Repository
import com.irzstudio.movieapps.model.discover.Discover
import com.irzstudio.movieapps.model.trending.PosterTrending
import com.irzstudio.movieapps.model.upcoming.PosterUpcoming
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: Repository): ViewModel() {

    private val _discoverResponseList = MutableLiveData<ArrayList<Discover>>()
    val discoverResponseList: LiveData<ArrayList<Discover>> = _discoverResponseList

    private val _trendingResponseList = MutableLiveData<ArrayList<PosterTrending>>()
    val trendingResponseList: LiveData<ArrayList<PosterTrending>> = _trendingResponseList

    private val _upcomingResponseList = MutableLiveData<ArrayList<PosterUpcoming>>()
    val upcomingResponseList: LiveData<ArrayList<PosterUpcoming>> = _upcomingResponseList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun requestDiscover() = viewModelScope.launch {
        repository.getDiscover().let {
            if (it.isSuccessful) {
                _discoverResponseList.postValue(it.body()?.results)
            } else {
                _errorMessage.postValue(it.message())
            }
        }
    }

    fun requestTrending() = viewModelScope.launch {
        repository.getTrending().let {
            if (it.isSuccessful) {
                _trendingResponseList.postValue(it.body()?.results)
            } else {
                _errorMessage.postValue(it.message())
            }
        }
    }

    fun requestUpcoming() = viewModelScope.launch {
        repository.getUpcoming().let {
            if (it.isSuccessful) {
                _upcomingResponseList.postValue(it.body()?.results)
            } else {
                _errorMessage.postValue(it.message())
            }
        }
    }

}