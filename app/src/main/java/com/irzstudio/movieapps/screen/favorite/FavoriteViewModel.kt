package com.irzstudio.movieapps.screen.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irzstudio.movieapps.model.datailfilm.DetailResponse
import com.irzstudio.movieapps.model.favorite.FavoriteEntity
import io.realm.Realm

class FavoriteViewModel : ViewModel() {

    private val _detailResponse = MutableLiveData<DetailResponse>()
    val detailResponse: LiveData<DetailResponse> = _detailResponse

    private val _favoriteEntityList = MutableLiveData<ArrayList<FavoriteEntity>>()
    val favoriteEntity: LiveData<ArrayList<FavoriteEntity>> = _favoriteEntityList

    fun loadFovoriteData() {
        val favoriteList: List<FavoriteEntity> = Realm.getDefaultInstance()
            .copyFromRealm(Realm.getDefaultInstance().where(FavoriteEntity::class.java).findAll())
    }

    fun removeMovie() {
        val detail = _detailResponse.value!!

        Realm.getDefaultInstance().executeTransaction {
            it.where(FavoriteEntity::class.java).equalTo("id", detail.id).findAll()
                .deleteFirstFromRealm()
        }
    }

}