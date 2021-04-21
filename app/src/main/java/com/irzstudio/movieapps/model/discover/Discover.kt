package com.irzstudio.movieapps.model.discover

import com.google.gson.annotations.SerializedName

data class Discover(
    @SerializedName("id")
    val id : Int,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("original_title")
    val title: String
)
