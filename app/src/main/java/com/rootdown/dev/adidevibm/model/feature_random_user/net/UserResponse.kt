package com.rootdown.dev.adidevibm.model.feature_random_user.net

import com.rootdown.dev.adidevibm.model.feature_random_user.db.User
import kotlinx.serialization.SerialName


data class UserResponse(
    @SerialName("results") val results: List<User>
)