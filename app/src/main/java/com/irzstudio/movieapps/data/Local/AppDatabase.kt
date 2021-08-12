package com.irzstudio.movieapps.data.Local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.irzstudio.movieapps.model.favorite.FavoriteDao
import com.irzstudio.movieapps.model.favorite.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

}