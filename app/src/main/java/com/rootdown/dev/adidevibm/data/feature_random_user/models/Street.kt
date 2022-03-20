package com.rootdown.dev.adidevibm.data.feature_random_user.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Street(
    val number: Long? = null,
    val name: String? = null
): Parcelable