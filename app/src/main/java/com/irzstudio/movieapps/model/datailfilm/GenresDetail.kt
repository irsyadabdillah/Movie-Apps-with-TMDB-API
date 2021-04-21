package com.irzstudio.movieapps.model.datailfilm

import com.google.gson.annotations.SerializedName

class GenresDetail(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val nameGenre: String
)