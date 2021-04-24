package com.irzstudio.movieapps.model.favorite

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class FavoriteEntity(
    @PrimaryKey
    var id: Int = 0,
    var backdropPath: String? = null,
    var genres: RealmList<GenreEntity> = RealmList(),
    var posterPath: String = "",
    var originalTitle: String = "",
    var releaseDate: String = "",

) : RealmObject()



