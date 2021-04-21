package com.irzstudio.movieapps.model.favorite

import com.irzstudio.movieapps.model.datailfilm.GenresDetail
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class FavoriteEntity(
    @PrimaryKey
    var id: Int,
    var backdropPath: String,
    var genres: RealmList<GenreEntity>,
    var posterPath: String,
    var originalTitle: String,
    var releaseDate: String,
) : RealmObject()
