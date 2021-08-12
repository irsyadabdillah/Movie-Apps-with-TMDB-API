package com.irzstudio.movieapps.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irzstudio.movieapps.data.Repository.Repository
import com.irzstudio.movieapps.model.favorite.FavoriteEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(val repository: Repository) : ViewModel() {

    private val _favoriteEntityList = MutableLiveData<ArrayList<FavoriteEntity>>()
    val favoriteEntityList: LiveData<ArrayList<FavoriteEntity>> = _favoriteEntityList

    private var dataFavorite: ArrayList<FavoriteEntity> = ArrayList()

    fun loadFovoriteData() {
        dataFavorite.clear()
        dataFavorite.addAll(repository.getAllDb())
        _favoriteEntityList.postValue(dataFavorite)
    }

    fun removeMovie(id:Int) {
        repository.removeMovie(id)
        loadFovoriteData()
    }

}
