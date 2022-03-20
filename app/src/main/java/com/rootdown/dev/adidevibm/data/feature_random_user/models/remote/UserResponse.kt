package com.rootdown.dev.adidevibm.data.feature_random_user.models.remote

import com.rootdown.dev.adidevibm.data.feature_random_user.models.UserX


data class UsersResponse(
    var info: Info?,
    var results: List<UserX>
) {
    data class Info(
        var page: Int?,
        var results: Int?,
        var seed: String?,
        var version: String?
    )
}