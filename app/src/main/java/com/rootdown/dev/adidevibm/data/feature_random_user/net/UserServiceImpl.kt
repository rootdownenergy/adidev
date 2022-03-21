package com.rootdown.dev.adidevibm.data.feature_random_user.net

import com.rootdown.dev.adidevibm.data.feature_random_user.models.remote.UsersResponse
import com.rootdown.dev.adidevibm.data.feature_random_user.net.util.HttpRoutes
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import retrofit2.Call

class UserServiceImpl(
    private val client: HttpClient
) : UserService, ApiService {

    override suspend fun getuser() : String {
        val httpResponse: HttpResponse = client.get(HttpRoutes.BASE_URL)
        return httpResponse.receive()
    }

    override fun loadUser(): Call<UsersResponse> {
        TODO("Not yet implemented")
    }

    override fun loadUsers(result: Int): Call<UsersResponse> {
        TODO("Not yet implemented")
    }
}