package com.rootdown.dev.adidevibm.model.feature_random_user.net

import com.rootdown.dev.adidevibm.model.feature_random_user.db.Users
import com.rootdown.dev.adidevibm.model.feature_random_user.net.util.HttpRoutes
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import java.lang.Exception

class UserServiceImpl(
    private val client: HttpClient
) : UserService {

    override suspend fun getuser() : String {
        val httpResponse: HttpResponse = client.get(HttpRoutes.BASE_URL)
        return httpResponse.receive()
    }
}