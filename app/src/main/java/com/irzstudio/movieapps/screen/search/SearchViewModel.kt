package com.irzstudio.movieapps.screen.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irzstudio.movieapps.model.discover.DiscoverResponse
import com.irzstudio.movieapps.model.search.SearchMovie
import com.irzstudio.movieapps.model.search.SearchResponse
import com.irzstudio.movieapps.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private val _searchMovieList = MutableLiveData<ArrayList<SearchMovie>>()
    val searchMovieList: LiveData<ArrayList<SearchMovie>> = _searchMovieList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String> = _errorMessage

    fun requestMovieQuery(query:String?){
        RetrofitClient.instance.getSearchMovie(query.orEmpty()).enqueue(object : Callback<SearchResponse>{
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                val searchResponse : SearchResponse = response.body()!!
                _searchMovieList.postValue(searchResponse.results)
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                t.printStackTrace()
                _errorMessage.postValue(t.localizedMessage)
            }

        })
    }
}