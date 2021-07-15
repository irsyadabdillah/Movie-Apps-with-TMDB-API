package com.irzstudio.movieapps

import android.app.Application
import android.content.Context
import com.irzstudio.movieapps.di.dataModule
import com.irzstudio.movieapps.di.viewModelModule
import com.irzstudio.movieapps.model.favorite.FavoriteDatabase
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppController)
            modules(dataModule)
            modules(viewModelModule)
        }

        Realm.init(this)


        val config = RealmConfiguration.Builder()
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()

        Realm.setDefaultConfiguration(config)

    }


}