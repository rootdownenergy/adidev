package com.rootdown.dev.adidevibm.data.feature_random_user.models

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Timezone(
    val offset: String? = null,
    val description: String? = null
): Parcelable