package com.irzstudio.movieapps.listener

import com.irzstudio.movieapps.model.datailfilm.DetailResponse
import com.irzstudio.movieapps.model.favorite.FavoriteEntity

interface OnClickListener {
    fun onClickFav(favoriteEntity: FavoriteEntity)
}