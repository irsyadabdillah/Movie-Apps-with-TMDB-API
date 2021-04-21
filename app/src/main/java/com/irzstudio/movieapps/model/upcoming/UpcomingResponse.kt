package com.irzstudio.movieapps.model.upcoming

import com.google.gson.annotations.SerializedName

data class UpcomingResponse(
    @SerializedName("results")
    val results: ArrayList<PosterUpcoming>
)
