package com.rootdown.dev.adidevibm.data.feature_random_user.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Picture(
    val large: String? = null,
    val medium: String? = null,
    val thumbnail: String? = null
): Parcelable