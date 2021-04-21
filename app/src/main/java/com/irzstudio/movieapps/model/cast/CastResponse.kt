package com.irzstudio.movieapps.model.cast

import com.google.gson.annotations.SerializedName

data class CastResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("cast")
    val cast: ArrayList<Cast>
)
