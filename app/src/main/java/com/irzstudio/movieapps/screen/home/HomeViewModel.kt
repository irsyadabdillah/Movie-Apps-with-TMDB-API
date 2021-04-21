package com.irzstudio.movieapps.screen.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irzstudio.movieapps.remote.RetrofitClient
import com.irzstudio.movieapps.model.discover.Discover
import com.irzstudio.movieapps.model.discover.DiscoverResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _discoverResponseList = MutableLiveData<ArrayList<Discover>>()
    val discoverResponseList: LiveData<ArrayList<Discover>> = _discoverResponseList

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
}