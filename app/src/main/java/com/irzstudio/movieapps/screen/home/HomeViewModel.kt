package com.irzstudio.movieapps.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irzstudio.movieapps.remote.RetrofitClient
import com.irzstudio.movieapps.model.discover.Discover
import com.irzstudio.movieapps.model.discover.DiscoverResponse
import com.irzstudio.movieapps.model.trending.PosterTrending
import com.irzstudio.movieapps.model.trending.TrendingResponse
import com.irzstudio.movieapps.model.upcoming.PosterUpcoming
import com.irzstudio.movieapps.model.upcoming.UpcomingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _discoverResponseList = MutableLiveData<ArrayList<Discover>>()
    val discoverResponseList: LiveData<ArrayList<Discover>> = _discoverResponseList

    private val _trendingResponseList = MutableLiveData<ArrayList<PosterTrending>>()
    val trendingResponseList: LiveData<ArrayList<PosterTrending>> = _trendingResponseList

    private val _upcomingResponseList = MutableLiveData<ArrayList<PosterUpcoming>>()
    val upcomingResponseList: LiveData<ArrayList<PosterUpcoming>> = _upcomingResponseList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun requestDiscover() {
        RetrofitClient.instance.getDiscover().enqueue(object : Callback<DiscoverResponse> {
            override fun onResponse(
                call: Call<DiscoverResponse>,
                response: Response<DiscoverResponse>
            ) {
                val discoverResponse : DiscoverResponse = response.body()!!
                _discoverResponseList.postValue(discoverResponse.results)
            }

            override fun onFailure(call: Call<DiscoverResponse>, t: Throwable) {
                t.printStackTrace()
               _errorMessage.postValue(t.localizedMessage)
            }

        })

    }

    fun requestTrending(){
        RetrofitClient.instance.getTrending().enqueue(object : Callback<TrendingResponse> {
            override fun onResponse(
                call: Call<TrendingResponse>,
                response: Response<TrendingResponse>
            ) {
                val trendingResponse : TrendingResponse = response.body()!!
                _trendingResponseList.postValue(trendingResponse.results)
            }

            override fun onFailure(call: Call<TrendingResponse>, t: Throwable) {
                t.printStackTrace()
                _errorMessage.postValue(t.localizedMessage)
            }

        })
    }

    fun requestUpcoming(){
        RetrofitClient.instance.getUpcoming().enqueue(object : Callback<UpcomingResponse> {
            override fun onResponse(
                call: Call<UpcomingResponse>,
                response: Response<UpcomingResponse>
            ) {
                val upcomingResponse : UpcomingResponse = response.body()!!
                _upcomingResponseList.postValue(upcomingResponse.results)
            }

            override fun onFailure(call: Call<UpcomingResponse>, t: Throwable) {
                t.printStackTrace()
                _errorMessage.postValue(t.localizedMessage)
            }

        })
    }

}