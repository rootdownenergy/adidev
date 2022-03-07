package com.rootdown.dev.adidevibm.model.feature_random_user.net

import android.util.Log
import com.rootdown.dev.adidevibm.model.feature_random_user.db.Users
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*

private const val TIME_OUT = 60_000

interface UserService {
    suspend fun getuser(): String
}