package com.irzstudio.movieapps.di

import com.irzstudio.movieapps.data.Repository
import com.irzstudio.movieapps.model.favorite.FavoriteDatabase
import com.irzstudio.movieapps.remote.Api
import com.irzstudio.movieapps.remote.RetrofitClient
import org.koin.dsl.module

val dataModule = module {
    single {
        RetrofitClient.instance
    }

    single {
        FavoriteDatabase.getInstance()
    }

    factory {
        Repository(get<Api>(), get<FavoriteDatabase>())
    }
}