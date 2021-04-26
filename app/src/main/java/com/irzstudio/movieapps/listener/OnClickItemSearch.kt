package com.irzstudio.movieapps.listener

import com.irzstudio.movieapps.model.search.SearchMovie
import com.irzstudio.movieapps.model.search.SearchResponse

interface OnClickItemSearch {
    fun onClick(searchMovie: SearchMovie)
}