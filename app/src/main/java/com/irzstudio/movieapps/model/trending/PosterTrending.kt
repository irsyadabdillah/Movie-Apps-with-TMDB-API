package com.irzstudio.movieapps.model.trending

import com.google.gson.annotations.SerializedName

data class PosterTrending(
    @SerializedName("id")
    val id : Int,
    @SerializedName("poster_path")
    val posterPath: String
)
