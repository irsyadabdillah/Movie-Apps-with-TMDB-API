package com.irzstudio.movieapps.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irzstudio.movieapps.data.Repository
import com.irzstudio.movieapps.remote.RetrofitClient
import com.irzstudio.movieapps.model.discover.Discover
import com.irzstudio.movieapps.model.discover.DiscoverResponse
import com.irzstudio.movieapps.model.trending.PosterTrending
import com.irzstudio.movieapps.model.trending.TrendingResponse
import com.irzstudio.movieapps.model.upcoming.PosterUpcoming
import com.irzstudio.movieapps.model.upcoming.UpcomingResponse
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(val repository: Repository): ViewModel() {

    private val _discoverResponseList = MutableLiveData<ArrayList<Discover>>()
    val discoverResponseList: LiveData<ArrayList<Discover>> = _discoverResponseList

    private val _trendingResponseList = MutableLiveData<ArrayList<PosterTrending>>()
    val trendingResponseList: LiveData<ArrayList<PosterTrending>> = _trendingResponseList

    private val _upcomingResponseList = MutableLiveData<ArrayList<PosterUpcoming>>()
    val upcomingResponseList: LiveData<ArrayList<PosterUpcoming>> = _upcomingResponseList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    fun requestDiscover() {
        val discoverDisposable = repository.getDiscover()
            .doOnSubscribe {  }
            .doFinally {  }
            .subscribe({_discoverResponseList.postValue(it.results)},{_errorMessage.postValue(it.localizedMessage)})
        compositeDisposable.add(discoverDisposable)
    }

    fun requestTrending(){
        val trendingDisposable = repository.getTrending()
            .doOnSubscribe {  }
            .doFinally {  }
            .subscribe({_trendingResponseList.postValue(it.results)}, {_errorMessage.postValue(it.localizedMessage)})
       compositeDisposable.add(trendingDisposable)
    }

    fun requestUpcoming(){
        val upcomingDisposable = repository.getUpcoming()
            .doOnSubscribe {  }
            .doFinally {  }
            .subscribe({_upcomingResponseList.postValue(it.results)}, {_errorMessage.postValue(it.localizedMessage)})
        compositeDisposable.add(upcomingDisposable)
    }

}