package com.irzstudio.movieapps

import android.app.Application
import io.realm.Realm

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

    }
}