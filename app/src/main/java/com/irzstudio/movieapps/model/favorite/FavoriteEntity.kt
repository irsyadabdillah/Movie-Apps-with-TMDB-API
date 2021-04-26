package com.irzstudio.movieapps.model.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "backdropPath")
    val backdropPath: String?,
    @ColumnInfo(name = "posterPath")
    val posterPath: String,
    @ColumnInfo(name = "originalTitle")
    val originalTitle: String,
    @ColumnInfo(name = "releaseDate")
    val releaseDate: String
)





