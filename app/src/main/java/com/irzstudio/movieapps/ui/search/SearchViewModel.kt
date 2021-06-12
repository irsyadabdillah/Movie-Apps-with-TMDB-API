package com.irzstudio.movieapps.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irzstudio.movieapps.data.Repository
import com.irzstudio.movieapps.model.search.SearchMovie
import com.irzstudio.movieapps.model.search.SearchResponse
import com.irzstudio.movieapps.remote.RetrofitClient
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel(val repository: Repository) : ViewModel() {

    private val _searchMovieList = MutableLiveData<ArrayList<SearchMovie>>()
    val searchMovieList: LiveData<ArrayList<SearchMovie>> = _searchMovieList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    fun requestMovieQuery(query: String?) {
        val movieQueryDisposable = repository.getMovieQuery(query.orEmpty())
            .doOnSubscribe { }
            .doFinally { }
            .subscribe(
                { _searchMovieList.postValue(it.results) },
                { _errorMessage.postValue(it.localizedMessage) })
        compositeDisposable.add(movieQueryDisposable)
    }
}