package com.irzstudio.movieapps.model.favorite

import io.realm.RealmObject

open class GenreEntity (
    var id: Int,
    var name: String

) : RealmObject()