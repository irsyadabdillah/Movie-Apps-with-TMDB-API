package com.irzstudio.movieapps.screen.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irzstudio.movieapps.model.datailfilm.DetailResponse
import com.irzstudio.movieapps.model.favorite.FavoriteDatabase
import com.irzstudio.movieapps.model.favorite.FavoriteEntity
import io.realm.Realm

class FavoriteViewModel : ViewModel() {

    private val _favoriteEntityList = MutableLiveData<ArrayList<FavoriteEntity>>()
    val favoriteEntityList: LiveData<ArrayList<FavoriteEntity>> = _favoriteEntityList

    private var database: FavoriteDatabase? = null

    init {
        database = FavoriteDatabase.getInstance()
    }

    private var dataFavorite: ArrayList<FavoriteEntity> = ArrayList()

    fun loadFovoriteData() {
        dataFavorite.clear()
        val dataFromBd = database?.favoriteDao()?.getAll().orEmpty()
        dataFavorite.addAll(dataFromBd)
        _favoriteEntityList.postValue(dataFavorite)
    }

    fun removeMovie(id:Int) {
        database?.favoriteDao()?.deleteById(id)
        loadFovoriteData()
    }

}
