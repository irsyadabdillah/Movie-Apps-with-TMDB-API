package com.irzstudio.movieapps

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        val config = RealmConfiguration.Builder()
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .build()

        Realm.setDefaultConfiguration(config)

    }
}