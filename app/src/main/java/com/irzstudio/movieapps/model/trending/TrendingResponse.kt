package com.irzstudio.movieapps.model.trending

import com.google.gson.annotations.SerializedName

data class TrendingResponse(
    @SerializedName("results")
    val results : ArrayList<PosterTrending>

)
