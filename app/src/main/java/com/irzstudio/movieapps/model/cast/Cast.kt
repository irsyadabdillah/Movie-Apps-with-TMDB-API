package com.irzstudio.movieapps.model.cast

import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_name")
    val name: String,
    @SerializedName("profile_path")
    val profil: String
)
