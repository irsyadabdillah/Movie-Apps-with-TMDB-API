package com.irzstudio.movieapps.model.datailfilm

import com.google.gson.annotations.SerializedName

data class DetailResponse(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genres")
    val genres: List<GenresDetail>,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val description: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("runtime")
    val duration: Int
)
