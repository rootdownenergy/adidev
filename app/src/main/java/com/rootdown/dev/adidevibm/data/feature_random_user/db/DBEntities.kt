package com.rootdown.dev.adidevibm.data.feature_random_user.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val userId: Int = 0,

) : Parcelable
