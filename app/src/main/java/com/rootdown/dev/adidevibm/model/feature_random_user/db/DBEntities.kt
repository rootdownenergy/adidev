package com.rootdown.dev.adidevibm.model.feature_random_user.db

import kotlinx.serialization.Serializable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Serializable
@Entity(tableName = "users")
data class Users(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var uid: String? = null,
    var name: String? = null,
    var location: String? = null,
    var login: String? = null,
    var dob: String? = null,
    var registered: String? = null,
    var picture: String? = null,
    var gender: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var cell: String? = null,
    var nat: String? = null,
)




