package com.irzstudio.movieapps.screen.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irzstudio.movieapps.model.cast.CastResponse
import com.irzstudio.movieapps.remote.RetrofitClient
import com.irzstudio.movieapps.model.datailfilm.DetailResponse
import com.irzstudio.movieapps.model.favorite.FavoriteEntity
import com.irzstudio.movieapps.model.favorite.GenreEntity
import io.realm.Realm
import io.realm.RealmList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {
    private val _detailResponse = MutableLiveData<DetailResponse>()
    val detailResponse: LiveData<DetailResponse> = _detailResponse

    private val _castResponse = MutableLiveData<CastResponse>()
    val castResponse: LiveData<CastResponse> = _castResponse

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun requestDetailMovie(id: Int) {
        RetrofitClient.instance.getDetailMovie(id).enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                response.body()?.let {
                    _detailResponse.postValue(it)
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                t.printStackTrace()
                _errorMessage.postValue(t.localizedMessage)
            }


        })
    }

    fun requestCast(id: Int){
        RetrofitClient.instance.getCast(id).enqueue(object : Callback<CastResponse>{
            override fun onResponse(call: Call<CastResponse>, response: Response<CastResponse>) {
                response.body()?.let {
                    _castResponse.postValue(it)
                }
            }

            override fun onFailure(call: Call<CastResponse>, t: Throwable) {
                t.printStackTrace()
                _errorMessage.postValue(t.localizedMessage)
            }

        })
    }

    /*fun saveMovie() {
        val detail = _detailResponseList.value!!

        val genreEntities: List<GenreEntity> = detail.genres.map {
            GenreEntity(it.id, it.nameGenre)
        }
        val realmListEntities: RealmList<GenreEntity> = RealmList()
        realmListEntities.addAll(genreEntities)

        val favorite = FavoriteEntity(
            id = detail.id,
            genres = realmListEntities
        )

        Realm.getDefaultInstance().executeTransaction {
            it.insert(favorite)
        }

        val favoriteList : List<FavoriteEntity> = Realm.getDefaultInstance().copyFromRealm(Realm.getDefaultInstance().where(FavoriteEntity::class.java).findAll())

        val id : Int = 100
        val fav = Realm.getDefaultInstance().where(FavoriteEntity::class.java).equalTo("id", id).findFirst()
        val isMovieFavorite = fav != null
    }*/
}