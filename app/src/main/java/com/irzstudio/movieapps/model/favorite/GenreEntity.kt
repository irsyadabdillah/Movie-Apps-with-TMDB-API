package com.irzstudio.movieapps.model.favorite

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class GenreEntity (
    @PrimaryKey
    var id: Int = 0,
    var name: String = ""

) : RealmObject()