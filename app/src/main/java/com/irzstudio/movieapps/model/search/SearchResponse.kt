package com.irzstudio.movieapps.model.search

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("results")
    val results: ArrayList<SearchMovie>
)
