package com.irzstudio.movieapps.di

import com.irzstudio.movieapps.data.Repository
import com.irzstudio.movieapps.ui.detail.DetailViewModel
import com.irzstudio.movieapps.ui.favorite.FavoriteViewModel
import com.irzstudio.movieapps.ui.home.HomeViewModel
import com.irzstudio.movieapps.ui.search.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        DetailViewModel(get<Repository>())
    }
    viewModel {
        FavoriteViewModel(get<Repository>())
    }
    viewModel {
        HomeViewModel(get<Repository>())
    }
    viewModel {
        SearchViewModel(get<Repository>())
    }
}