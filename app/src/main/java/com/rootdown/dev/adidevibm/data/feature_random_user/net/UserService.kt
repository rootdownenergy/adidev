package com.rootdown.dev.adidevibm.data.feature_random_user.net

private const val TIME_OUT = 60_000

interface UserService {
    suspend fun getuser(): String
}