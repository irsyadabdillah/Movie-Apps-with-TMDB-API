package com.irzstudio.movieapps.model.upcoming

import com.google.gson.annotations.SerializedName

data class PosterUpcoming(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("original_title")
    val title: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("genre_ids")
    val genre: List<Int>
)
