package com.irzstudio.movieapps.listener

import com.irzstudio.movieapps.model.favorite.FavoriteEntity

interface OnClickItemFavorite {
    fun onClick(favoriteEntity: FavoriteEntity)
    fun onClickFav(favoriteEntity: FavoriteEntity)
}