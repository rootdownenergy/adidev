package com.rootdown.dev.adidevibm.data.feature_random_user.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var uid: String? = null,
    var namefirst: String? = null,
    var namelast: String? = null,
    var location: String? = null,
    var city: String? = null,
    var state: String? = null,
    var login: String? = null,
    var dob: String? = null,
    var registered: String? = null,
    var picturesm: String? = null,
    var picturemd: String? = null,
    var gender: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var cell: String? = null,
    var nat: String? = null,
)




