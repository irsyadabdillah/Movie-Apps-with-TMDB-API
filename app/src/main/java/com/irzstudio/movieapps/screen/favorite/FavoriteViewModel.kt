package com.irzstudio.movieapps.screen.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irzstudio.movieapps.model.favorite.FavoriteEntity
import io.realm.Realm

class FavoriteViewModel : ViewModel(){



    private var _favoriteEntityList = MutableLiveData<ArrayList<FavoriteEntity>>()
    var favoriteEntity: LiveData<ArrayList<FavoriteEntity>> = _favoriteEntityList

    fun loadFovoriteData(){
        val favoriteList: List<FavoriteEntity> = Realm.getDefaultInstance()
            .copyFromRealm(Realm.getDefaultInstance().where(FavoriteEntity::class.java).findAll())
    }
}