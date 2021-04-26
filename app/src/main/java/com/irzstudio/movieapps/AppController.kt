package com.irzstudio.movieapps

import android.app.Application
import android.content.Context
import com.irzstudio.movieapps.model.favorite.FavoriteDatabase
import io.realm.Realm
import io.realm.RealmConfiguration

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        INSTANCE = this

        val config = RealmConfiguration.Builder()
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()

        Realm.setDefaultConfiguration(config)

    }

    companion object{
        private var INSTANCE: AppController? = null

        @JvmStatic
        fun getInstance() : Context{
            return INSTANCE as AppController
        }

    }
}