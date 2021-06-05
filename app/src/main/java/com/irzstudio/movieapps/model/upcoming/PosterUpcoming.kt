package com.irzstudio.movieapps.model.upcoming

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class PosterUpcoming(
    @SerializedName("id")
    val id: Int,
    @SerializedName("vote_average")
    val rating: Float,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("original_title")
    val title: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("genre_ids")
    val genre: ArrayList<Int>
)
