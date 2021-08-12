package com.irzstudio.movieapps.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irzstudio.movieapps.data.Repository.Repository
import com.irzstudio.movieapps.model.search.SearchMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val _searchMovieList = MutableLiveData<ArrayList<SearchMovie>>()
    val searchMovieList: LiveData<ArrayList<SearchMovie>> = _searchMovieList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage


    fun requestMovieQuery(query: String?) = viewModelScope.launch {
        repository.getMovieQuery(query.orEmpty()).let {
            if (it.isSuccessful) {
                _searchMovieList.postValue(it.body()?.results)
            } else {
                _errorMessage.postValue(it.message())
            }
        }
    }
}